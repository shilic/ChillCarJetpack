package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

/**
 * 0x189C_1824 ABC分区温度和控制电压
 */
@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB5 (
    /** 温区A实际温度  <br></br>显示精度0.1℃、范围-40～140℃  */
    @CanBinding(messageId = CabinData.Cabin2CCS5_ID, signalTag = "emb_areaAActualTemp")
    var areaAActualTemp: Double? = null,
    /** 温区B实际温度 <br></br> 显示精度0.1℃、范围-40～140℃  */
    @CanBinding(messageId = CabinData.Cabin2CCS5_ID, signalTag = "emb_areaBActualTemp")
    var areaBActualTemp: Double? = null,
    /** 温区C实际温度<br></br>  显示精度0.1℃、范围-40～140℃  */
    @CanBinding(messageId = CabinData.Cabin2CCS5_ID, signalTag = "emb_areaCActualTemp")
    var areaCActualTemp: Double? = null,
    /** 控制电压 ; ; <br></br>庆铃标准 : 显示精度0.1V、范围0-36.0V  */
    @CanBinding(messageId = CabinData.Cabin2CCS5_ID, signalTag = "emb_ctrlVoltage")
    var ctrlVoltage: Double? = null,
) : CanCopyable<EMB5>, Cloneable  {
    override fun copyNew(): EMB5 {
        return clone()
    }
    override fun clone(): EMB5 {
        return super.clone() as EMB5
    }
} // 0x189C_1824 ABC分区温度和控制电压