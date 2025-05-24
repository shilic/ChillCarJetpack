package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB6(
    /** 高压直流电压 ; ; <br></br>庆铃标准 : 显示精度0.1V、范围0-6553.5V  */
    @CanBinding(messageId = CabinData.Cabin2CCS6_ID, signalTag = "emb_highDCVoltage")
    var highDCVoltage: Double? = null,
    /** 高压直流电流 ; ; <br></br>庆铃标准 : 显示精度0.1A、范围0-6553.5A  */
    @CanBinding(messageId = CabinData.Cabin2CCS6_ID, signalTag = "emb_highDCCurrent")
    var highDCCurrent: Double? = null,
    /** 压缩机转速 ; ;<br></br> 庆铃标准 : 显示精度1rpm、范围0-65535rpm  */
    @CanBinding(messageId = CabinData.Cabin2CCS6_ID, signalTag = "emb_compSpeed")
    var compSpeed: Double? = null,
    /** 机组累计运行时间 ; ;<br></br> 庆铃标准 : 显示精度1h、范围0-65535h  */
    @CanBinding(messageId = CabinData.Cabin2CCS6_ID, signalTag = "emb_runningTime")
    var runningTime: Double? = null
): CanCopyable<EMB6>, Cloneable  {
    override fun copyNew(): EMB6 {
        return clone()
    }
    override fun clone(): EMB6 {
        return super.clone() as EMB6
    }
}
