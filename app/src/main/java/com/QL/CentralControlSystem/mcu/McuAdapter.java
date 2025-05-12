package com.QL.CentralControlSystem.mcu;

import android.mcu.Can;
import android.mcu.McuManager;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

import quickCanResolver.tool.SLCTool;
import quickCanResolver.core.CanIo;
import quickCanResolver.core.CanListenService;
import quickCanResolver.core.McuService;

/** MCU适配器。<br>
 * 因为为了适配第三方 的CAN收发 组件McuCan。故采用适配器模式，用于适配不同的底层实现。上层直接使用接口，底层变动，上层不需要变动。 */
public class McuAdapter implements McuService  {
    private static final String LogTag = "Mcu适配器";
    /** 最终交给底层的 McuCan 来实现。第三方组件。不可变动。 同时，第三方的组件和现有的接口有所不同，故采用适配器模式，适配现有接口。 */
    private Can mcuCan = McuManager.getInstance().getCanInstance(); /*原始，获取到can实例*/
    private McuCanListenerImp mcuCanListenerImp ;

    public McuAdapter() {
        Log.d(LogTag,"适配器 McuAdapter 实例化完成。本地mcu接口实例化完成");
    }
    /**
     * 手动 重启CAN实例 <br>
     * 下边的代码异常关键，系统曾经出现杀进程后再重启时无法正常接收报文的故障；加上了下边两行代码之后，故障消除，目的是重启CAN实例
     */
    private void reBootCan() {
        mcuCan.unregisterCan(mcuCanListenerImp); /* 手动销毁 */
        mcuCan = McuManager.getInstance().getCanInstance(); /*手动重启，获取CAN实例*/
    }

    @Override
    public void nativeSend(int canId, byte[] data8) {
        // 最终调用了本地的方法来实现报文的发送
        //mcuCan.nativeSendCanData(canId, data8);
        int[] ReqData = {  0x67 , 0x45 , 0x23 , 0x81 ,  0x08,  0x00,0x00,0x00,0x00    ,0x00,0x00,0x00,0x00 };
        mcuCan.sendCanCommand(ReqData, ReqData.length);
    }
    //在主活动中调用注册之后，最终会回到这里进行注册。这里传入主活动的监听函数。
    @Override
    public void nativeRegister(CanListenService canListener) {
        // 生成一个本地的监听函数
        mcuCanListenerImp = McuCanListenerImp.getInstance().addCanListener(canListener) ;
        // 这里会首先回调本地的方法调用。先调用第三方组件的方法，传入自己的监听回调后，在内部由本地方法再调用。
        /* 先手动销毁之前的实例 */
        mcuCan.unregisterCan(mcuCanListenerImp);
        /* 手动重启，获取CAN实例 */
        mcuCan = McuManager.getInstance().getCanInstance();
        /* 再重新注册。 */
        mcuCan.registerCan(mcuCanListenerImp);
        Log.d(LogTag,"成功获取本地CAN实例，并注册监听事件");
    }

    @Override
    public void nativeUnRegister(CanListenService canListener) {
        /* 销毁之前的实例 */
        if(mcuCanListenerImp != null && mcuCan!= null ){
            mcuCan.unregisterCan(mcuCanListenerImp);
        }
    }
    public static class McuCanListenerImp extends Can.CanListener {
        private static final String LogTag = "McuCanListenerImp";
        CanListenService canListener ;
        private static volatile McuCanListenerImp that;

        private McuCanListenerImp() {
            Log.d(LogTag,"本地监听函数实例化完成");
        }
        public McuCanListenerImp  addCanListener(CanListenService canListener) {
            this.canListener = canListener;
            Log.d(LogTag,"成功添加监听事件");
            return that;
        }
        /**
         * 监听事件对象,<br>原始函数， 报文来的时候会执行下边的代码。
         */
        @Override
        public void onStatus(int cmd, Bundle bundle) {
            byte[] rawData = bundle.getByteArray(Can.CAN_STATUS_BUNDLE_CAN_DATA); /*原始，从包裹中获取CAN数据*/
            // 获取到上报的can数据 rawData :  [ AA,55,0F,C1,   24,18,98,18,  08,   11,22,33,44,55,66,77,FF,   6F, ]
            // 接受到原始报文 rawData : {AA, 55, 0F, C1, 24, 18, 98, 18, 08, 00, 01, 02, 03, 04, 05, 06, 07, 2E, }准备传入函数进行解析
            Log.d( LogTag, "接受到原始报文 rawData : " + SLCTool.toHexString(rawData) + "准备传入函数进行解析"  )   ; /*原始*/
            // 拿到第三方的数据后， 最终回调了我自己写的监听函数。
            // 首先进行数据的解析。
            int canId = SLCTool.from4bytesToInt(Arrays.copyOfRange(rawData, 4, 8)  , SLCTool.DataType.Intel);
            byte[] data8 = Arrays.copyOfRange(rawData, 9, 17);
            // 解码报文
            //CanIo.getInstance().manager.deCode_B(canId, data8);
            // 再回调从 Activity 传入的回调函数
            canListener.listened(canId,data8);
        }   //onStatus
        public static McuCanListenerImp getInstance() {
            if (that == null) {
                synchronized(McuCanListenerImp.class) {
                    if (that == null) {
                        return that = new McuCanListenerImp();
                    }
                }
            }

            return that;
        } // getInstance()
    } // public static class McuCanListenerImp extends Can.CanListener

} // public class McuAdapter implements McuService

