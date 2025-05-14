package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.cabinType.CabinData.Companion.Cabin2CCS2_ID
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

/**
 * 0x1899_1824 ，进阶参数，一般不使用
 */
@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB2 (
    /** 箱内温度校正 H6 ; 分辨率：1℃/bit 范围-10℃~10℃ */
    @CanBinding(messageId = Cabin2CCS2_ID, signalTag = "emb_boxTempCorrection")
    var boxTempCorrection: Double = 0.0
)  : CanCopyable<EMB2>, Cloneable {
    override fun copyNew(): EMB2 {
        return clone()
    }
    override fun clone(): EMB2 {
        return super.clone() as EMB2
    }
}