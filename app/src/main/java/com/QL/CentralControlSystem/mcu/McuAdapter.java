package com.QL.CentralControlSystem.mcu;

import android.mcu.Can;
import android.mcu.McuManager;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LifecycleObserver;

import QuickCanResolver.CanTool.MyByte;
import QuickCanResolver.Core.CanIo;
import QuickCanResolver.Core.CanListenService;
import QuickCanResolver.Core.McuService;

/** 因为为了适配第三方组件McuCan。故采用适配器模式，用于适配不同的底层实现。上层直接使用接口，底层变动，上层不需要变动。 */
public class McuAdapter implements McuService  {
    /** 最终交给底层的 McuCan 来实现。第三方组件。不可变动。 同时，第三方的组件和现有的接口有所不同，故采用适配器模式，适配现有接口。 */
    private Can mcuCan = McuManager.getInstance().getCanInstance(); /*原始，获取到can实例*/
    private McuCanListenerImp mcuCanListenerImp ;

//    public McuAdapter() {
//    }
    /**
     * 手动 重启CAN实例 <br>
     * 下边的代码异常关键，系统曾经出现杀进程后再重启时无法正常接收报文的故障；加上了下边两行代码之后，故障消除，目的是重启CAN实例
     */
    private void reBootCan() {
        mcuCan.unregisterCan(mcuCanListenerImp); /* 手动销毁 */
        mcuCan = McuManager.getInstance().getCanInstance(); /*手动重启，获取CAN实例*/
    }

    @Override
    public void nativeSend(int canId, int[] data8) {
        // 最终调用了本地的方法来实现报文的发送
        //mcuCan.nativeSendCanData(canId, data8);
        int[] ReqData = {  0x67 , 0x45 , 0x23 , 0x81 ,  0x08,  0x00,0x00,0x00,0x00    ,0x00,0x00,0x00,0x00 };
        mcuCan.sendCanCommand(ReqData, ReqData.length);
    }
    @Override
    public void nativeRegister(CanListenService canListener) {
        mcuCanListenerImp = new McuCanListenerImp(canListener);
        // 这里会首先回调本地的方法调用。先调用第三方组件的方法
        mcuCan.registerCan(mcuCanListenerImp);
    }

    @Override
    public void nativeUnRegister(CanListenService canListener) {
        mcuCan.unregisterCan(mcuCanListenerImp);
    }
    public static class McuCanListenerImp extends Can.CanListener {
        CanListenService canListener ;
        public McuCanListenerImp(CanListenService canListener){
            this.canListener = canListener;
        }
        /**
         * 监听事件对象,<br>原始函数， 报文来的时候会执行下边的代码。
         */
        @Override
        public void onStatus(int cmd, Bundle bundle) {
            byte[] data = bundle.getByteArray(Can.CAN_STATUS_BUNDLE_CAN_DATA); /*原始，从包裹中获取CAN数据*/
            // 获取到上报的can数据 data :  [ AA,55,0F,C1,   24,18,98,18,  08,   11,22,33,44,55,66,77,FF,   6F, ]
            Log.d( "接收CAN数据", "接受到原始报文 data : " + MyByte.hex2_Str(data) + "准备传入函数进行解析"  )   ; /*原始*/
            // 拿到第三方的数据后， 最终回调了我自己写的监听函数。
            // 首先进行数据的解析。
            int canId = 0;
            byte[] data8 = new byte[0];
            CanIo.getInstance().manager.deCode_B(canId, data8);
            // 再回调从 Activity 传入的回调函数
            canListener.listened(canId);
        }   //onStatus
    }
}
