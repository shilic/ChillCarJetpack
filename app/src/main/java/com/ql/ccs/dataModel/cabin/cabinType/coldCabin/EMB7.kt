package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB7(
    /** 蒸发风机电压 ; ;<br></br> 庆铃标准 : 显示精度0.1V、范围0-6553.5V  */
    @CanBinding(messageId = CabinData.Cabin2CCS7_ID, signalTag = "emb_evaporatorVoltage")
    var evaporatorVoltage: Double? = null,
    /** 冷凝风机电压 ; ;<br></br> 庆铃标准 : 显示精度0.1V、范围0-6553.5V  */
    @CanBinding(messageId = CabinData.Cabin2CCS7_ID, signalTag = "emb_condenserVoltage")
    var condenserVoltage: Double? = null,
    /** 变频器运行状态标志（INV状态） ; ; <br></br>庆铃标准 : 0x0:预留 ; 0x1：正转运行 ; 0x2：反转运行 ; 0x3：停机
     * 0x4：调谐 ; 0x5：故障 ; 6：预留 ; 7：无效值未使用  */
    @CanBinding(messageId = CabinData.Cabin2CCS7_ID, signalTag = "emb_freqStatus")
    var freqStatus: Int = 0,
    /** DC-DC1状态标志 ; ; <br></br>庆铃标准 : 0x0：无效 ; 0x1：准备就绪 ; 0x2：工作 ; 0x3：停机 ; 0x4：故障 ; 5-6：预留 ; 7：无效值未使用  */
    @CanBinding(messageId = CabinData.Cabin2CCS7_ID, signalTag = "emb_dc_dc1_Sts")
    var dc_dc1_Sts: Int = 0,
    /** DC-DC2状态标志 ; ;<br></br> 庆铃标准 : 0x0：无效 ; 0x1：准备就绪 ; 0x2：工作 ; 0x3：停机 ; 0x4：故障 ; 5-6：预留 ; 7：无效值未使用  */
    @CanBinding(messageId = CabinData.Cabin2CCS7_ID, signalTag = "emb_dc_dc2_Sts")
    var dc_dc2_Sts: Int = 0,
    /** 制冷剂高压压力 ; ; <br></br>庆铃标准 : 显示精度0.1bar、范围0-6553.5bar  */
    @CanBinding(messageId = CabinData.Cabin2CCS7_ID, signalTag = "emb_refrigerantPressure")
    var refrigerantPressure: Double? = null
) : CanCopyable<EMB7>, Cloneable  {
    override fun copyNew(): EMB7 {
        return clone()
    }
    override fun clone(): EMB7 {
        return super.clone() as EMB7
    }
}
