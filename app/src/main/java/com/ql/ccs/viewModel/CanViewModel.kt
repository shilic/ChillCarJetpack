package com.ql.ccs.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB1
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB2
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB3
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB4
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB5
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB6
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB7
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB8
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.CanIo

/**
 * CAN视图模型，需要在UI层中完成生命周期的监听，更新页面。
 */
class CanViewModel : ViewModel(){
    // 创建可变的LiveData私有变量（仅ViewModel内可修改）
    private val _emb1Data = MutableLiveData<EMB1>()
    private val _emb2Data = MutableLiveData<EMB2>()
    private val _emb3Data = MutableLiveData<EMB3>()
    private val _emb4Data = MutableLiveData<EMB4>()
    private val _emb5Data = MutableLiveData<EMB5>()
    private val _emb6Data = MutableLiveData<EMB6>()
    private val _emb7Data = MutableLiveData<EMB7>()
    private val _emb8Data = MutableLiveData<EMB8>()
    // 公开的不可变LiveData（外部只读访问）
    val emb1LiveData: LiveData<EMB1> get() = _emb1Data
    val emb2LiveData: LiveData<EMB2> get() = _emb2Data
    val emb3LiveData: LiveData<EMB3> get() = _emb3Data
    val emb4LiveData: LiveData<EMB4> get() = _emb4Data
    val emb5LiveData: LiveData<EMB5> get() = _emb5Data
    val emb6LiveData: LiveData<EMB6> get() = _emb6Data
    val emb7LiveData: LiveData<EMB7> get() = _emb7Data
    val emb8LiveData: LiveData<EMB8> get() = _emb8Data
    init {
        Log.d(LogTag,"CAN视图模型 初始化完成")
    }
    /** 使用 CanId 更新数据 */
    fun updateFromCan(canId: Int, data8: ByteArray) {
        when(canId) {
            CabinData.Cabin2CCS1_ID -> _emb1Data.postValue(createData(EMB1::class.java))
            CabinData.Cabin2CCS2_ID -> _emb2Data.postValue(createData(EMB2::class.java))
            CabinData.Cabin2CCS3_ID -> _emb3Data.postValue(createData(EMB3::class.java))
            CabinData.Cabin2CCS4_ID -> _emb4Data.postValue(createData(EMB4::class.java))
            CabinData.Cabin2CCS5_ID -> _emb5Data.postValue(createData(EMB5::class.java))
            CabinData.Cabin2CCS6_ID -> _emb6Data.postValue(createData(EMB6::class.java))
            CabinData.Cabin2CCS7_ID -> _emb7Data.postValue(createData(EMB7::class.java))
            CabinData.Cabin2CCS8_ID -> _emb8Data.postValue(createData(EMB8::class.java))
        }
    }
    /** 直接使用数据更新 viewModel  */
    @SuppressWarnings("unused")
    fun <T : CanCopyable<T>> update(dataModel : T){
        when(dataModel){
            is EMB1 -> _emb1Data.postValue(dataModel)
            is EMB2 -> _emb2Data.postValue(dataModel)
            is EMB3 -> _emb3Data.postValue(dataModel)
            is EMB4 -> _emb4Data.postValue(dataModel)
            is EMB5 -> _emb5Data.postValue(dataModel)
            is EMB6 -> _emb6Data.postValue(dataModel)
            is EMB7 -> _emb7Data.postValue(dataModel)
            is EMB8 -> _emb8Data.postValue(dataModel)
        }
    }
    /** 根据ID 判断类，然后使用类名获取新的数据 */
    private fun <T : CanCopyable<T>> createData(clazz: Class<T>): T {
        // 示例数据 ： newEmb1 =EMB1(tempSetValue=21, workMode=1, defrostStage=2, collisionWarningSts=0, sterilizeSts=1,
        // defrostIntervalA1=0, defrostMaxTimeA3=0, defrostEndTempA4=-40, evaporationFanTempModeH5=0)
        return CanIo.Manager().createNewModel(clazz).also {
            Log.d(LogTag, "${clazz.name} = $it")
        }
    }

    companion object{
        private const val LogTag = "CanViewModel"
    }
}