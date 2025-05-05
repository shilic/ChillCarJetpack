package com.QL.CentralControlSystem.dataModel.Cabin.CabinType.ColdCabin

import android.util.Log
import com.QL.CentralControlSystem.dataModel.Cabin.EnumVar.EMBError
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * EMB上装，继承自 制冷上装 ColdCabin
 */
class EMBCabin : ColdCabin() {
    @Deprecated("")
    val emb_cabinCtrl_Id = 0x18FF0019
    /* 发送数据 */
    /** 参数设置有效条目 ; <br></br>庆铃标准 : <br></br>0x0：非设置状态 ; <br></br>0x1：除霜间隔时间 A1 ; <br></br>0x2:  预留 ;<br></br> 0x3: 除霜运行最长时间 A3 ;
     * <br></br>0x4：除霜终止温度 A4 ;<br></br> 0x5：蒸发风机到温模式 H5 ;<br></br> 0x6：箱内温度校正 H6 ;<br></br> 0x7：温区A温度设定
     * <br></br>8：温区B温度设定 ; <br></br>9：温区C温区设定 ; <br></br>10-15：预留  */
    @Deprecated("")
    var paramSelect = 0

    /** 普通参数设置状态标识  <br></br>emb标准 : <br></br>0x00  非设置状态 ; <br></br>0x01  除霜间隔时间 A1 ; <br></br>0x02  温控回差温度 A2 ;<br></br> 0x03  除霜运行最长时间 A3
     * <br></br>0x04  除霜终止温度A4 ; <br></br>0x05-0xFF  无效  */
    var normalParamSelect = 0
    /*普通参数和高级参数都为0时，设定值变为设定温度。*/
    /** 高级参数设置状态标识; <br></br> emb标准 : <br></br> 0x00  非设置状态 ;<br></br> 0x01  制热功能使能 H1 ;<br></br> 0x02  滴水时间 H2 ; <br></br>0x03  库温设定上限温度 H3
     * <br></br>0x04  库温设定上限温度 H4 ; <br></br>0x05  蒸发风机到温模式 H5 ; <br></br>0x06  箱内温度校正 H6 ; <br></br>0x07  压缩机最短停机时间H7
     * <br></br>0x08  除霜温差模式 H8 ; <br></br>0x09  当量除霜温差 H9 ; <br></br>0x0A  温差除霜最高除霜温度限制值H10 ; <br></br>0x0B  面板配置 H11
     * <br></br> 0x0C  排气温度控制喷液阀H12 ; <br></br>0x0D  打开喷液阀的排气温度阀值H13 ; <br></br>0x0E  关闭喷液阀的排气温度阀值H14 ;<br></br> 0x0F  压缩机转速上限设置H15 ;<br></br> 0x10-0xFF  无效 */
    var advancedParamSelect = 0

    /** 设定值加1 ，同时表示设定 开启; emb标准 : 0x00  无效 ; 0x01  有效 ; 庆铃标准 : 0x0: 无效值未使用 ; 0x1:加1℃  */
    var setValueUp_req = 0

    /** 设定值减1 ，同时表示设定 关闭; emb标准 : 0x00  无效 ; 0x01  有效 ; 庆铃标准 : 0x0: 无效值未使用 ; 0x1: 减1℃  */
    var setValueDown_req = 0

    /** 强制开关机 ; emb标准 : 0x00  无效 ; 0x01  有效 ; */
    var forceSwitch_req = 0
    /* 接收数据 */
    /** 数据区间标志位 ; <br></br>0x0：预留 ;<br></br> 0x1：区间1，分区温度设定值。 ;<br></br> 0x2：区间2，分区温度实际值 ;<br></br> 0x3：区间3，高级参数设置的实际值。
     * <br></br>0x4：区间4，机组工作状态 ; <br></br>0x5：区间5，各部位温度 ;<br></br> 0x06：区间6，电压电流一 ;<br></br> 0x07：区间7，电压电流二
     * <br></br>0x08：机组累计运行时间 ;<br></br> 9-15：预留  */
    @Deprecated("")
    var cabinDataArea = 0

    /** 除霜间隔时间 A1 ; 分辨率：30min/bit 范围30~600  */
    var defrostInterval = 400

    /** 除霜运行最长时间 A3 ; 分辨率：1min/bit 范围1~60  */
    var defrostMaxTime = 40

    /**  除霜终止温度 A4 ; 分辨率：1℃/bit 范围1℃~30℃ */
    var defrostEndTemp = 15

    /** 蒸发风机到温模式 H5 ; emb标准 :0x00=关闭 ; 0x01=开启 ; 庆铃标准0x0:预留 ; 0x1:关 ; 0x2:开 ; 0x3:无效值未使用  */
    var evaporationFanTempMode = 0

    /** 箱内温度校正 H6 ; 分辨率：1℃/bit 范围-10℃~10℃ */
    var boxTempCorrection = 0.0

    /** 当前故障，键表示故障码，值表示故障码枚举  */
    var currentErrorMap = HashMap<Int, EMBError?>()

    /** 历史故障，键表示故障码，值表示故障码枚举  */
    var historyErrorMap = HashMap<Int, EMBError?>()

    /** 故障缓存，键表示故障码，值表示故障码枚举  */
    var bufferErrorMap = HashMap<Int, EMBError?>()

    /** 最高 故障等级，默认为 0 ,故障等级，1级故障不显示（但可查询），2级故障主界面显示，3级故障表示严重故障 , 4 非常严重故障 */
    var maxFaultLevel = 0

    /** 缓存 故障等级  */
    private var bufferErrLevel = 0
    /* 6月7日 ，新增 10个 设备选配 报文 Config */
    /** 制热功能使能 <br></br> 0x0 = 未使能 ； 0x1 = 使能  */
    var warmConfig = 0

    /** 防撞雷达选配 <br></br> 0:未选配; 1:选配 ;  */
    var collisionRadarConfig = 0

    /** 消毒功能选配  */
    var sterilizeConfig = 0

    /** 温区A温度传感器 选配  */
    var areaATempSensorConfig = 0

    /** 温区B温度传感器 选配  */
    var areaBTempSensorConfig = 0

    /** 温区C温度传感器 选配  */
    var areaCTempSensorConfig = 0

    /** 箱门开关传感器 选配  */
    var doorSwitchSensorConfig = 0

    /** 中控屏配置(中控屏不处理)  */
    var CCSConfig = 0

    /** VCU通讯配置(中控屏不处理)  */
    var VCUConfig = 0

    /** 自带远程模块配置(中控屏不处理)  */
    var remoteCtrlConfig = 0

    /**
     * 故障管理器,负责处理并分析接受到的故障码 , Cabin2CCS3_ID = 0x189A_1824
     * @param errCode 传入当前故障码
     */
    private fun manageFaults(errCode: Int) {
        if (errCode == 97) { // 屏蔽97 远程通信模块故障
            return
        }
        val fault: EMBError?
        if (errCode > 0x00) {  // errCode != 0x00 如果发生了故障
            if (errCode != 0xFF) { //如果故障码没有循环一圈，即表示正在发送故障码。遂向历史故障中添加故障码，向故障缓存中添加故障码
                fault = EMBError.getErrByCode(errCode)
                if (fault != null) {
                    val level = fault.errorLevel //获取故障等级
                    if (level > bufferErrLevel) {  //如果新的故障等级大于旧的故障等级
                        bufferErrLevel = level //记录最高故障等级到缓存变量。
                    }
                    //在这里向故障码添加 故障发生的时间
                    // 获取当前日期和时间
                    val currentDateTime = LocalDateTime.now()
                    // 定义日期格式
                    val dateFormatter =
                        DateTimeFormatter.ofPattern("yy-MM-dd HH:mm") // "yyyy-MM-dd HH:mm:ss"
                    // 格式化日期为字符串
                    val formattedDate = currentDateTime.format(dateFormatter)
                    fault.errTime = formattedDate
                }
                historyErrorMap[errCode] = fault
                bufferErrorMap[errCode] = fault
            } else {  // errCode ==0xFF , 故障码循环一圈
                //如果循环一圈。首先,清空当前故障 。然后,将 故障缓存 中的故障码转移到 当前故障 中。最后,清空缓存
                currentErrorMap.clear() //清空 当前故障
                /* 有可能发生 0x00 0xFF循环发的情况，故，在操作前需要判断缓存不为 empty . */
                //使用 putAll() 方法将源 Map 的所有映射关系添加到目标 Map 中之后，再使用 clear() 方法清空源 Map 不会影响目标 Map 中的内容。
                // 这是因为 putAll() 方法只是复制了源 Map 的映射关系，而不是引用。所以，一旦映射关系复制到目标 Map 中，目标 Map 就独立于源 Map。
                if (!bufferErrorMap.isEmpty()) {  //有故障码，并且故障缓存不为空
                    currentErrorMap.putAll(bufferErrorMap) //将缓存中的故障 全部 添加到当前故障中
                } // else currentErrorMap.isEmpty() == true ;
                bufferErrorMap.clear() //清空缓存
                maxFaultLevel = bufferErrLevel // 将缓存的故障等级设置为当前故障等级。
                bufferErrLevel = 0 //清空故障缓存，重新设置故障等级为 0 ; 以便于在下次记录最高故障等级的时候重新计算
            }
        } //如果发生了故障
        else if (errCode == 0x00) {  //  errCode == 0 没有发生故障。清空 当前故障 ，清空故障缓存。
            Log.d("故障管理器", "故障信息为空")
            maxFaultLevel = 0 //故障等级设置为0
            bufferErrLevel = 0
            currentErrorMap.clear()
            bufferErrorMap.clear() //清空缓存
            //currentErrorMap.put(errCode,fault);
        } //没有发生故障
        //currentErrorMap.put(errCode,fault);
        Log.d("故障管理器", "添加故障码 , errCode = $errCode")
    }

    /** 得到一串请求开关机的报文  */
    fun switchReqData(): IntArray {
        switch_req = 1
        return compileSendData(CCS7_ID_) //得到一串请求开关机的报文
    }
    /*这段代码非常必要，因为开关机都是发的 1 ，所以必须在使用之后 ，让 开关请求的变量变成0 ，否则后续操作会一直发1，导致程序反复开关机*/
    /** 得到一串 没有 请求开关机的报文  */
    fun switchReq0() {
        switch_req = 0
        sw_req2 = 0
        modeReq2 = 0
    }

    /** 得到一串 请求制热 的报文  */
    fun warmOnReqData(): IntArray {
        warmMode_req = 1
        coldMode_req = 0
        fanMode_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 得到一串 请求制冷 的报文  */
    fun coldOnReqData(): IntArray {
        warmMode_req = 0
        coldMode_req = 1
        fanMode_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 得到一串 请求通风 的报文  */
    fun fanOnReqData(): IntArray {
        warmMode_req = 0
        coldMode_req = 0
        fanMode_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 得到一段没有工作模式的报文  */
    fun modeReqData0() {
        warmMode_req = 0
        coldMode_req = 0
        fanMode_req = 0
        modeReq2 = 0
    }

    /** 得到一段 请求手动除霜 开启或者关闭 的报文  */
    fun defrostOnReqData(): IntArray {
        defrost_req = 1
        return compileSendData(CCS7_ID_)
    }

    /**
     * 得到一段  没有  请求手动除霜  的报文
     */
    fun defrostReqData0() {
        defrost_req = 0
        defrost_req2 = 0
        // compileSendData(CCS7_ID_);
    }

    /** 得到一段 请求臭氧杀菌 开启或者关闭 的报文  */
    fun sterilizeOnReqData(): IntArray {
        sterilize_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 得到一段  没有  请求臭氧杀菌  的报文  */
    fun sterilizeReqData0() {
        sterilize_req = 0
        sterilize_req2 = 0
        // compileSendData(CCS7_ID_);
    }

    /** 获得一段 请求设定温度 上升 的报文  */
    fun setTempValueUpReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 0
        setValueUp_req = 1
        setValueDown_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段 请求设定温度 下降 的报文  */
    fun setTempValueDownReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 0
        setValueUp_req = 0
        setValueDown_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段 没有 设定参数 的报文  */
    fun setParamValue0ReqData() {
        normalParamSelect = 0
        advancedParamSelect = 0
        setValueUp_req = 0
        setValueDown_req = 0
    }

    /** 获得一段设置 除霜间隔时间 A1 上升 的报文 */
    fun defrostIntervalUpReqData(): IntArray {
        normalParamSelect = 1
        advancedParamSelect = 0
        setValueUp_req = 1
        setValueDown_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 除霜间隔时间 A1 下降 的报文 */
    fun defrostIntervalDownReqData(): IntArray {
        normalParamSelect = 1
        advancedParamSelect = 0
        setValueUp_req = 0
        setValueDown_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 运行最长时间  A3 上升 的报文 */
    fun defrostMaxTimeUpReqData(): IntArray {
        normalParamSelect = 3
        advancedParamSelect = 0
        setValueUp_req = 1
        setValueDown_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 运行最长时间 A3 下降 的报文 */
    fun defrostMaxTimeDownReqData(): IntArray {
        normalParamSelect = 3
        advancedParamSelect = 0
        setValueUp_req = 0
        setValueDown_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 除霜终止温度 A4 上升 的报文 */
    fun defrostEndTempUpReqData(): IntArray {
        normalParamSelect = 4
        advancedParamSelect = 0
        setValueUp_req = 1
        setValueDown_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 除霜终止温度 A4 下降 的报文 */
    fun defrostEndTempDownReqData(): IntArray {
        normalParamSelect = 4
        advancedParamSelect = 0
        setValueUp_req = 0
        setValueDown_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 箱内温度校正 H6 上升 的报文 */
    fun boxTempCorrectionUpReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 6
        setValueUp_req = 1
        setValueDown_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 箱内温度校正 H6 下降 的报文 */
    fun boxTempCorrectionDownReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 6
        setValueUp_req = 0
        setValueDown_req = 1
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 蒸发风机到温模式 H5 开启 的报文 */
    fun evaFanTempModeOnReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 5
        setValueUp_req = 1
        setValueDown_req = 0
        return compileSendData(CCS7_ID_)
    }

    /** 获得一段设置 蒸发风机到温模式 H5 关闭 的报文 */
    fun evaFanTempModeOffReqData(): IntArray {
        normalParamSelect = 0
        advancedParamSelect = 5
        setValueUp_req = 0
        setValueDown_req = 1
        return compileSendData(CCS7_ID_)
    }

    /* 新增冷王冷机的控制 */
    fun sw_req2OnData() {
        sw_req2 = 1
        modeReq2 = 1
        //return compileSendData(CCS7_ID_);
    }

    fun sw_req2OffData() {
        sw_req2 = 2
        //return compileSendData(CCS7_ID_);
    }

    fun modeReq2ColdData() {
        modeReq2 = 1
        //return compileSendData(CCS7_ID_);
    }

    fun modeReq2WarmData() {
        modeReq2 = 2
        //return compileSendData(CCS7_ID_);
    }

    fun modeReq2FanData() {
        modeReq2 = 3
        //return compileSendData(CCS7_ID_);
    }

    fun defrost_req2OnData() {
        defrost_req2 = 1
        //return compileSendData(CCS7_ID_);
    }

    fun defrost_req2OffData() {
        defrost_req2 = 2
        //return compileSendData(CCS7_ID_);
    }

    fun sterilize_req2OnData() {
        sterilize_req2 = 1
        //return compileSendData(CCS7_ID_);
    }

    fun sterilize_req2OffData() {
        sterilize_req2 = 2
        //return compileSendData(CCS7_ID_);
    }

    companion object {
        private const val LogTag = "EMBCabin"
    }
}