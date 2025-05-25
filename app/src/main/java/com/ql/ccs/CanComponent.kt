package com.ql.ccs

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB1
import quickCanResolver.core.CanIo
import quickCanResolver.core.CanListenService

/**
 * CAN组件，需要在主活动中完成生命周期的监听。
 */
class CanComponent : ViewModel(), CanListenService , DefaultLifecycleObserver  {
    init {
        Log.d(LogTag,"CAN组件初始化完成")
    }
    /* // @OnLifecycleEvent 注解会存在反射的性能开销，故官方弃用了该组件，改用了 LifecycleEventObserver 接口 ，或者 DefaultLifecycleObserver 接口更方便 */
    override fun onCreate(owner: LifecycleOwner) {
        // 注册回调函数
        CanIo.getInstance().register(this)
    }
    override fun onDestroy(owner: LifecycleOwner) {
        // 当生命周期进入 DESTROYED 状态时，系统会自动移除观察者（但显式移除更安全）
        owner.lifecycle.removeObserver(this)
        CanIo.getInstance().unRegisterCanListener()
    }
    override fun listened(canId: Int, data8: ByteArray) {
        //Log.d(LogTag,"最终，主活动的监听被回调, 被监听的报文ID = ${SLCTool.toHexString(canId)}"  )
        when(canId){
            CabinData.Cabin2CCS1_ID -> handle1()
            CabinData.Cabin2CCS2_ID -> handle2()
            CabinData.Cabin2CCS3_ID -> handle3()
            CabinData.Cabin2CCS4_ID -> handle4()
            CabinData.Cabin2CCS5_ID -> handle5()
            CabinData.Cabin2CCS6_ID -> handle6()
            CabinData.Cabin2CCS7_ID -> handle7()
            CabinData.Cabin2CCS8_ID -> handle8()
        }
    }
    private fun handle1(){
        // 获取新的数据之后，现在就可以直接拿新的数据进行界面的刷新操作了
        val newEmb1 : EMB1 =  CanIo.Manager().createNewModel(EMB1::class.java)
        // 示例数据 ： newEmb1 =EMB1(tempSetValue=21, workMode=1, defrostStage=2, collisionWarningSts=0, sterilizeSts=1,
        // defrostIntervalA1=0, defrostMaxTimeA3=0, defrostEndTempA4=-40, evaporationFanTempModeH5=0)
        Log.d(LogTag, "newEmb1 =$newEmb1")

    }
    private fun handle2(){

    }
    private fun handle3(){

    }
    private fun handle4(){

    }
    private fun handle5(){

    }
    private fun handle6(){

    }
    private fun handle7(){

    }
    private fun handle8(){

    }
    // 可选：获取 Context 的扩展方法
    private fun LifecycleOwner.getContext(): Context {
        return when (this) {
            is Context -> this
            is Fragment -> requireContext()
            else -> throw IllegalStateException("无效的 LifecycleOwner")
        }
    }
    companion object{
        private const val LogTag = "CanComponent"
    }



}