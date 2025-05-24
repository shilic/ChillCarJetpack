package com.ql.ccs

import android.util.Log
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB1
import quickCanResolver.core.CanIo
import quickCanResolver.core.CanListenService
import quickCanResolver.tool.SLCTool

/**
 * 实现数据监听回调接口
 */
class CanListenImp : CanListenService {
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

    companion object{
        private const val LogTag = "CanListenImp"
    }
}