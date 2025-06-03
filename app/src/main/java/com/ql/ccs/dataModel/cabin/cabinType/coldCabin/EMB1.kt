package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.cabinType.CabinData.Companion.Cabin2CCS1_ID
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding

/**
 * 0x18981824; 空调主要信息和参数，包括设定温度和工作模式
 */
@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB1 (
    /** 温度设定值 <br></br>分辨率：1℃/bit 偏移量：-40℃  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_tempSetValue")
    var tempSetValue: Int = 0,
    /** 机组工作模式 ;<br></br>
     *  emb标准 :庆铃标准 :0x0：预留 (表示关机); 0x1：制冷 ; 0x2：制热 ; 0x3：仅通风 ;
     *  4-14：预留 ; 15：无效值未使用  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_workMode")
    var workMode: Int = 0,
    /** 除霜阶段标志 ; <br>
     * emb标准 : 0x00 = 除霜关闭 ; <br>
     * 0x01=  除霜准备 ;  <br>
     * 0x02 = 除霜运行 ;  <br>
     * 0x03  = 滴水 。 <br>
     * 除了0之外的状态都是除霜开启。  <br> */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_defrostStage")
    var defrostStage: Int = 0,
    /** 车辆碰撞预警状态 ;<br></br> emb : 0x00 = 大于1.5米，无防撞提示;
     * <br></br>0x01 = 距离非常近，小于0.3 M;
     * <br></br>0x02 = 距离很近，大于0.3米，小于0.6米;
     * <br></br>0x03 = 距离一般，大于0.6米，小于0.9米;
     * <br></br>0x04 = 距离远，大于0.9米，小于1.2米;
     * <br></br>0x05 = 距离稍远，大于1.2米，小于1.5米;
     * <br></br>庆铃标准 :0x0：预留 ; 0x1：不报警 ; 0x2：距离过近，报警 ; 0x3：无效值未使用  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_collisionWarningSts")
    var collisionWarningSts: Int = 0,
    /** 杀菌消毒状态 ;<br></br>
     * emb标准 : 0x00  = 关闭 ; 0x01  =  开启; <br></br>
     * 庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_sterilizeSts")
    var sterilizeSts: Int = 0,

    /** 除霜间隔时间 A1 ; 分辨率：30min/bit 范围30~600  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_defrostIntervalA1")
    var defrostIntervalA1: Int = 400,

    /** 除霜运行最长时间 A3 ; 分辨率：1min/bit 范围1~60  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_defrostMaxTimeA3")
    var defrostMaxTimeA3: Int = 40,

    /**  除霜终止温度 A4 ; 分辨率：1℃/bit 范围1℃~30℃ */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_defrostEndTempA4")
    var defrostEndTempA4: Int = 15,

    /** 蒸发风机到温模式 H5 ; emb标准 :0x00=关闭 ; 0x01=开启 ; 庆铃标准0x0:预留 ; 0x1:关 ; 0x2:开 ; 0x3:无效值未使用  */
    @CanBinding(messageId = Cabin2CCS1_ID, signalTag = "emb_evaporationFanTempModeH5")
    var evaporationFanTempModeH5: Int = 0
): CanCopyable<EMB1>, Cloneable {
    override fun copyNew(): EMB1 {
        return clone()
    }
    override fun clone(): EMB1 {
        return super.clone() as EMB1
    }
} // EMB1