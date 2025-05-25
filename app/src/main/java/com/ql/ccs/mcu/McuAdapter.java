package com.ql.ccs.mcu;

import android.mcu.Can;
import android.mcu.McuManager;
import android.os.Bundle;
import android.util.Log;

import com.ql.ccs.tool.NumberToolKt;

import java.util.Arrays;

import quickCanResolver.core.CanIo;
import quickCanResolver.tool.SLCTool;
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

    @Override
    public void nativeSend(int canId, byte[] data8) {
        // 最终调用了本地的方法来实现报文的发送
        int[] mcuData = new int[13];
        int[] id_ = SLCTool.intTo4BytesI(canId, SLCTool.DataType.Intel); //0x1898_2418。  18 98 24 18 将ID变成数组形式
        System.arraycopy(id_, 0, mcuData, 0, 4); //存入ID; [ 18,24,98,18,   0,   0,0,0,0,  0,0,0,0, ] //高位放高位，故反向
        mcuData[4] = 8;  // 存入长度8 , [ 18,24,98,18,  8,  0,0,0,0,  0,0,0,0, ]
        int[] frame8 = NumberToolKt.toIntArray(data8); // java可以调用扩展函数吗？
        System.arraycopy(frame8, 0, mcuData, 5,8); //存入 8位byte的数据 frame // [ 18,24,98,18,  8,  2,3E,F,0,  0,0,0,0, ]
        mcuCan.sendCanCommand(mcuData, mcuData.length);
    }
    /* 此数据用于获取历史报文
     int cmdId = 0X8123_4567; //指令Id  0x67, 0x45, 0x23,0x81,  //0x81, 0x23, 0x45,0x67,
        int[] ReqData = {  0x67 , 0x45 , 0x23 , 0x81 ,  0x08,  0x00,0x00,0x00,0x00    ,0x00,0x00,0x00,0x00 };
    * */
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
        private CanListenService canListener ;
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
            // 接受到原始报文 rawData : {AA, 55, 0F, C1,    24, 18, 98, 18,    08,    00, 01, 02, 03, 04, 05, 06, 07,     2E, }准备传入函数进行解析
            //Log.d( LogTag, "接受到原始报文 rawData : " + SLCTool.toHexString(rawData) + " 准备传入函数进行解析"  )   ; /*原始*/
            // 拿到第三方的数据后， 最终回调了我自己写的监听函数。// 首先进行数据的解析。
            int canId = SLCTool.from4bytesToInt(Arrays.copyOfRange(rawData, 4, 8)  , SLCTool.DataType.Intel);
            byte[] data8 = Arrays.copyOfRange(rawData, 9, 17);
            // 解码报文
            CanIo.Manager().deCode_B(canId, data8);
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

