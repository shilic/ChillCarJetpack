package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB8(
    /** 字符串格式的软件版本号  */
    var softwareVersionNumStr: String? = null,
    /** 逻辑版本第一序号 , 例如 ‘V1.4.0’版本号中的第一位‘1’  */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_versionNum1")
    var softVersionNum1: Int = 0,
    /** 逻辑版本第二序号 , 例如 ‘V1.4.0’版本号中的第二位‘4’  */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_versionNum2")
    var softVersionNum2: Int = 0,
    /** 程序顺序版本，在程序逻辑版本未改动的条件下，每次编程修改后，该数值+1 ; 例如 ‘V1.4.0’版本号中的第三位‘0’ */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_sequenceNum")
    var sequenceNum: Int = 0,
    /** 软件型号（基本型部分）,例如 例如十进制值58，表示580中的58  */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_softwareBaseNum")
    var softBaseNum: Int = 0,
    /** 软件型号（变型部分）, ,例如 例如十进制值0，表示580中的0  */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_softwareModifyNum")
    var softModifyNum: Int = 0,
    /** 底盘厂家代号 , 例如17表示庆铃  */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_chassisFactoryNum")
    var chassisFactoryNum: Int = 0,
    /** 车辆型号 , 底盘规格代码号，该底盘厂家具体的底盘型号，例如1表示庆铃的新轻卡 */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_vehicleModel")
    var vehicleModel: Int = 0,
    /** 上装厂家代码,冷机厂家代码。 例如1表示EMB, */
    @CanBinding(messageId = CabinData.Cabin2CCS8_ID, signalTag = "pro_cabinFactorNum")
    var cabinFactorNum: Int = 0
) : CanCopyable<EMB8>, Cloneable  {
    override fun copyNew(): EMB8 {
        return clone()
    }
    override fun clone(): EMB8 {
        return super.clone() as EMB8
    }
}
