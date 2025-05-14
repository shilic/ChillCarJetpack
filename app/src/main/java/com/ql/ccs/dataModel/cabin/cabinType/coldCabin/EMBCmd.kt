package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.tool.toIntArray
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.CanIo
import quickCanResolver.core.DbcBinding

/**
 * EMB上装的控制指令
 */
@DbcBinding(value = [
        DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
class EMBCmd : CanCopyable<EMBCmd>, Cloneable {
    /* 发送数据 */
    /** 上装取电请求（发给VCU） ;<br>
     * 庆铃标准 : 0x0:预留 ; 0x1:无效;  0x2：有效 ; 0x3:无效值未使用 <br>   */
    @Deprecated("EMB没有使用这个值")
    var ptoHvOnReq = 0

    /** 请求机组工作(复位式)<br>
     * emb标准 (复位式) : 0x00  无效 ; 0x01  有效 (无论开关机都发1 ，根据 workMode 的值来改变 );<br>
     * 庆铃标准 : 0x0: 预留 ; 0x1:OFF（关机） ; 0x2：ON（开机） ; 0x3: 无效值未使用 <br> */
    @CanBinding(messageId = 0x18982418, signalTag = "emb_switch_req")
    var switchReq = 0

    /** 请求机组工作模式 ; <br></br>
     * 庆铃标准 :  0x0：预留 ; 0x1：制冷 ; 0x2：制热 ; 0x3: 仅通风 ;<br>
     * 4-14：预留 ; 15：无效值未使用 <br> */
    @Deprecated("EMB没有使用这个值")
    var workModeReq = 0

    /** 请求新风控制（内外循环）（预留） ;<br></br>
     * 庆铃标准 :  0x0: 预留 ; 0x1:OFF（关机） ;<br>
     * 0x2：ON（开机） ; 0x3: 无效值未使用 <br>  */
    @Deprecated("EMB没有使用这个值")
    var newWindReq = 0

    /** 请求手动除霜 ; <br></br>
     * emb标准 (复位式): 0x00  无效 ; 0x01  有效 ; <br></br>
     * 庆铃标准 : 0x0: 预留 ; 0x1:除霜关闭  ; 0x2：除霜开启 ; 0x3: 无效值未使用<br>   */
    @CanBinding(signalTag = "emb_defrost_req")
    var defrostReq = 0

    /** 请求杀菌控制 ; <br></br>
     * emb标准 (复位式): 0x00  无效 ; 0x01  有效 ; <br></br>
     * 庆铃标准 : 0x0: 预留 ; 0x1:OFF（关机） ; 0x2：ON（开机） ; 0x3: 无效值未使用   */
    @CanBinding(signalTag = "emb_sterilize_req")
    var sterilizeReq = 0

    /** 无效标志位 ; <br></br>庆铃标准 : 0x0：该帧报文无效 ; 0x1：该帧报文有效  */
    @Deprecated("暂不使用")
    var invalidFlag = 1

    /** 制冷模式 ; <br></br>emb标准 : 0x00  无效 ; 0x01  有效  */
    @CanBinding(signalTag = "emb_coldMode_req")
    var coldModeReq = 0

    /** 制热模式 ; <br></br>emb标准 : 0x00  无效 ; 0x01  有效  */
    @CanBinding(signalTag = "emb_warmMode_req")
    var warmModeReq = 0

    /** 通风模式 ; <br></br>emb标准 : 0x00  无效 ; 0x01  有效  */
    @CanBinding(signalTag = "emb_fanMode_req")
    var fanModeReq = 0

    @Deprecated("EMB的发送报文ID，我们不适用")
    val embCabinCtrlId = 0x18FF0019
    /* 发送数据 */
    /** 参数设置有效条目 ; <br></br>
     * 庆铃标准 : <br></br>
     * 0x0：非设置状态 ; <br></br>
     * 0x1：除霜间隔时间 A1 ; <br></br>
     * 0x2:  预留 ;<br></br>
     * 0x3: 除霜运行最长时间 A3 ;<br></br>
     * 0x4：除霜终止温度 A4 ;<br></br>
     * 0x5：蒸发风机到温模式 H5 ;<br></br>
     * 0x6：箱内温度校正 H6 ;<br></br>
     * 0x7：温区A温度设定 <br></br>
     * 8：温区B温度设定 ; <br></br>
     * 9：温区C温区设定 ; <br></br>
     * 10-15：预留  */
    @Deprecated("EMB没有使用这个值")
    var paramSelect = 0

    /** 普通参数设置状态标识  <br></br>emb标准 : <br></br>
     * 0x00  非设置状态 ; <br></br>
     * 0x01  除霜间隔时间 A1 ; <br></br>
     * 0x02  温控回差温度 A2 ;<br></br>
     * 0x03  除霜运行最长时间 A3 <br></br>
     * 0x04  除霜终止温度A4 ; <br></br>
     * 0x05-0xFF  无效  */
    @CanBinding(signalTag = "emb_normalParamSelect")
    var normalParamSelect = 0
    /*普通参数和高级参数都为0时，设定值变为设定温度。*/
    /** 高级参数设置状态标识; <br></br> emb标准 : <br></br>
     * 0x00  非设置状态 ;<br></br>
     * 0x01  制热功能使能 H1 ;<br></br>
     * 0x02  滴水时间 H2 ; <br></br>
     * 0x03  库温设定上限温度 H3 <br></br>
     * 0x04  库温设定上限温度 H4 ; <br></br>
     * 0x05  蒸发风机到温模式 H5 ; <br></br>
     * 0x06  箱内温度校正 H6 ; <br></br>
     * 0x07  压缩机最短停机时间H7 <br></br>
     * 0x08  除霜温差模式 H8 ; <br></br>
     * 0x09  当量除霜温差 H9 ; <br></br>
     * 0x0A  温差除霜最高除霜温度限制值H10 ; <br></br>
     * 0x0B  面板配置 H11 <br></br>
     * 0x0C  排气温度控制喷液阀H12 ; <br></br>
     * 0x0D  打开喷液阀的排气温度阀值H13 ; <br></br>
     * 0x0E  关闭喷液阀的排气温度阀值H14 ;<br></br>
     * 0x0F  压缩机转速上限设置H15 ;<br></br>
     * 0x10-0xFF  无效 */
    @CanBinding(signalTag = "emb_advancedParamSelect")
    var advancedParamSelect = 0

    /** 设定值加1 ，同时表示设定 开启; 设定数据“+”。<br></br>
     * 设定“数据加”和“数据减”不可以同时为1，否则系统不执行。<br></br>
     * 普通参数和高级参数全部为0时，表示设定温度。<br></br>
     * 并且普通参数和高级参数不可以同时为1，只能有一个为1 。<br></br>
     * emb标准 : 0x00  无效 ; 0x01  有效 ;<br></br>
     * 庆铃标准 : 0x0: 无效值未使用 ; 0x1:加1℃ <br></br> */
    @CanBinding(signalTag = "emb_setValueUp_req")
    var setValueUpReq = 0

    /** 设定值减1 ，同时表示设定 关闭; 设定数据“-” 。<br></br>
     * 设定“数据加”和“数据减”不可以同时为1，否则系统不执行。<br></br>
     * 普通参数和高级参数全部为0时，表示设定温度。<br></br>
     * 并且普通参数和高级参数不可以同时为1，只能有一个为1 。<br></br>
     * emb标准 : 0x00  无效 ; 0x01  有效 ; <br></br>
     * 庆铃标准 : 0x0: 无效值未使用 ; 0x1: 减1℃ <br></br> */
    @CanBinding(signalTag = "emb_setValueDown_req")
    var setValueDownReq = 0

    /** 强制开关机 ; emb标准 : 0x00  无效 ; 0x01  有效 ; */
    @Deprecated("预留，未做协议，不使用该信号")
    var forceSwitchReq = 0


    /** 得到一串请求开关机的报文  */
    fun switchReqData(): IntArray {
        switchReq = 1
        return compileSendData(CabinData.CCS7_ID_) //得到一串请求开关机的报文
    }
    /*这段代码非常必要，因为开关机都是发的 1 ，所以必须在使用之后 ，让 开关请求的变量变成0 ，否则后续操作会一直发1，导致程序反复开关机*/
    /** 得到一串 没有 请求开关机的报文  */
    fun switchReq0() {
        switchReq = 0
    }

    /** 得到一串 请求制热 的报文  */
    fun warmOnReqData(): IntArray {
        warmModeReq = 1
        coldModeReq = 0
        fanModeReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 得到一串 请求制冷 的报文  */
    fun coldOnReqData(): IntArray {
        warmModeReq = 0
        coldModeReq = 1
        fanModeReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 得到一串 请求通风 的报文  */
    fun fanOnReqData(): IntArray {
        warmModeReq = 0
        coldModeReq = 0
        fanModeReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 得到一段没有工作模式的报文  */
    fun modeReqData0() {
        warmModeReq = 0
        coldModeReq = 0
        fanModeReq = 0
    }

    /** 得到一段 请求手动除霜 开启或者关闭 的报文  */
    fun defrostOnReqData(): IntArray {
        defrostReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /**
     * 得到一段  没有  请求手动除霜  的报文
     */
    fun defrostReqData0() {
        defrostReq = 0
        // compileSendData(CCS7_ID_);
    }

    /** 得到一段 请求臭氧杀菌 开启或者关闭 的报文  */
    fun sterilizeOnReqData(): IntArray {
        sterilizeReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 得到一段  没有  请求臭氧杀菌  的报文  */
    fun sterilizeReqData0() {
        sterilizeReq = 0
        // compileSendData(CCS7_ID_);
    }

    /** 获得一段 请求设定温度 上升 的报文  */
    fun setTempValueUpReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 0
        setValueUpReq = 1
        setValueDownReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段 请求设定温度 下降 的报文  */
    fun setTempValueDownReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 0
        setValueUpReq = 0
        setValueDownReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段 没有 设定参数 的报文  */
    fun setParamValue0ReqData() {
        normalParamSelect = 0
        advancedParamSelect = 0
        setValueUpReq = 0
        setValueDownReq = 0
    }

    /** 获得一段设置 除霜间隔时间 A1 上升 的报文 */
    fun defrostIntervalUpReqData(): IntArray {
        normalParamSelect = 1
        advancedParamSelect = 0
        setValueUpReq = 1
        setValueDownReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 除霜间隔时间 A1 下降 的报文 */
    fun defrostIntervalDownReqData(): IntArray {
        normalParamSelect = 1
        advancedParamSelect = 0
        setValueUpReq = 0
        setValueDownReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 运行最长时间  A3 上升 的报文 */
    fun defrostMaxTimeUpReqData(): IntArray {
        normalParamSelect = 3
        advancedParamSelect = 0
        setValueUpReq = 1
        setValueDownReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 运行最长时间 A3 下降 的报文 */
    fun defrostMaxTimeDownReqData(): IntArray {
        normalParamSelect = 3
        advancedParamSelect = 0
        setValueUpReq = 0
        setValueDownReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 除霜终止温度 A4 上升 的报文 */
    fun defrostEndTempUpReqData(): IntArray {
        normalParamSelect = 4
        advancedParamSelect = 0
        setValueUpReq = 1
        setValueDownReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 除霜终止温度 A4 下降 的报文 */
    fun defrostEndTempDownReqData(): IntArray {
        normalParamSelect = 4
        advancedParamSelect = 0
        setValueUpReq = 0
        setValueDownReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 箱内温度校正 H6 上升 的报文 */
    fun boxTempCorrectionUpReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 6
        setValueUpReq = 1
        setValueDownReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 箱内温度校正 H6 下降 的报文 */
    fun boxTempCorrectionDownReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 6
        setValueUpReq = 0
        setValueDownReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 蒸发风机到温模式 H5 开启 的报文 */
    fun evaFanTempModeOnReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 5
        setValueUpReq = 1
        setValueDownReq = 0
        return compileSendData(CabinData.CCS7_ID_)
    }

    /** 获得一段设置 蒸发风机到温模式 H5 关闭 的报文 */
    fun evaFanTempModeOffReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 5
        setValueUpReq = 0
        setValueDownReq = 1
        return compileSendData(CabinData.CCS7_ID_)
    }

    /**
     * 得到一段发送报文
     */
    private fun compileSendData(sendId : Int): IntArray {
        return CanIo.Manager().enCode_B(sendId).toIntArray()
    }
    companion object {
        private const val LogTag = "EMBCmd"
    }
    override fun copyNew(): EMBCmd {
        return clone()
    }
    override fun clone(): EMBCmd {
        return super.clone() as EMBCmd
    }
}