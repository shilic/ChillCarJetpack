package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB4(
    /** 室外温度 ; ; <br></br>庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    @CanBinding(messageId = CabinData.Cabin2CCS4_ID, signalTag = "emb_outsideTemp")
    var outsideTemp: Double? = null,
    /** 出风温度 ; ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    @CanBinding(messageId = CabinData.Cabin2CCS4_ID, signalTag = "emb_airOutTemp")
    var airOutTemp: Double? = null,
    /** 除霜温度 ; ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    @CanBinding(messageId = CabinData.Cabin2CCS4_ID, signalTag = "emb_defrostTemp")
    var defrostTemp: Double? = null,
    /** 压缩机排气温度 ; ; <br></br>庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    @CanBinding(messageId = CabinData.Cabin2CCS4_ID, signalTag = "emb_compExhaustTemp")
    var compExhaustTemp: Double? = null
): CanCopyable<EMB4>, Cloneable  {
    override fun copyNew(): EMB4 {
        return clone()
    }
    override fun clone(): EMB4 {
        return super.clone() as EMB4
    }
}
