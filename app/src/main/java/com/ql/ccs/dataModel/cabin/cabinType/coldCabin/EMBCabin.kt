package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import android.util.Log
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.enumVar.EMBError
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * EMB上装，用于接收数据
 */
@DbcBinding(value = [
        DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
class EMBCabin : CanCopyable<EMBCabin>, Cloneable , Serializable {
    /* 接收数据 */
    /** 温区A设定温度  ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃  */
    var areaASetTemp = 0.0

    /** 温区B设定温度  ; <br></br>庆铃标准 : 显示精度0.1℃、范围-40～140℃  */
    var areaBSetTemp = 0.0

    /** 温区C设定温度  <br></br>显示精度0.1℃、范围-40～140℃  */
    var areaCSetTemp = 0.0

    /** 温区A实际温度  <br></br>显示精度0.1℃、范围-40～140℃  */
    var areaAActualTemp: Double? = null

    /** 温区B实际温度 <br></br> 显示精度0.1℃、范围-40～140℃  */
    var areaBActualTemp: Double? = null

    /** 温区C实际温度<br></br>  显示精度0.1℃、范围-40～140℃  */
    var areaCActualTemp: Double? = null

    /** 箱内相对湿度 ;<br></br> 显示精度1%、范围0～100%  */
    var relativeHumidity: Double? = null

    /** 温区A是否有效 ;<br></br> 0x0:无效 ; 0x1:有效  */
    @Deprecated("")
    var tempAreaAIf = 0

    /** 温区B是否有效 ;<br></br> 0x0:无效 ; 0x1:有效  */
    @Deprecated("")
    var tempAreaBIf = 0

    /** 温区C是否有效 ;<br></br> 0x0:无效 ; 0x1:有效  */
    @Deprecated("")
    var tempAreaCIf = 0

    /** 温度设定值 <br></br>分辨率：1℃/bit 偏移量：-40℃  */
    var tempSetValue = 0

    /** 机组工作状态 ; ; <br></br>庆铃标准 : 0x0:预留 ; 0x1:关机 ; 0x2:开机 ; 0x3:无效值未使用  */
    var workSwitchSts = 0

    /** 机组工作模式 ;<br></br> emb标准 :庆铃标准 :0x0：预留 (表示关机); 0x1：制冷 ; 0x2：制热 ; 0x3：仅通风 ; 4-14：预留 ; 15：无效值未使用  */
    var workMode = 0

    /** 机组除霜状态 ; ; <br></br>庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用  */
    @Deprecated("")
    var defrostSts = 0

    /** 除霜阶段标志 ; <br></br> emb标准 : 0x00 = 除霜关闭 ; 0x01=  除霜准备 ; 0x02 = 除霜运行 ; 0x03  = 滴水  */
    var defrostStage = 0

    /** 杀菌消毒状态 ;<br></br> emb标准 : 0x00  = 关闭 ; 0x01  =  开启; <br></br>庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用  */
    var sterilizeSts = 0

    /** 市电接入状态/供电状态 ;<br></br>emb标准:0x00 = 电池供电 ;0x01 = 备电供电   ; <br></br>庆铃标准 :0x0:预留 ; 0x1: 无市电接入，电池供电 ; 0x2：有市电接入，备电供电 ; 0x3：无效值未使用  */
    var backupPowerSts = 0

    /** 节能是否开启 ;<br></br>emb: 0x0 = 无效;0x1= 有效 ; <br></br>庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用   */
    var ECOSts = 0

    /** 远程控制状态 ;<br></br>emb :0x00  =面板优先;0x01 =远程控制优先 ;<br></br> 庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用  */
    var remoteControlSts = 0

    /** 车辆碰撞预警状态 ;<br></br> emb : 0x00 = 大于1.5米，无防撞提示;
     * <br></br>0x01 = 距离非常近，小于0.3 M;
     * <br></br>0x02 = 距离很近，大于0.3米，小于0.6米;
     * <br></br>0x03 = 距离一般，大于0.6米，小于0.9米;
     * <br></br>0x04 = 距离远，大于0.9米，小于1.2米;
     * <br></br>0x05 = 距离稍远，大于1.2米，小于1.5米;
     * <br></br>庆铃标准 :0x0：预留 ; 0x1：不报警 ; 0x2：距离过近，报警 ; 0x3：无效值未使用  */
    var collisionWarningSts = 0

    /** 门磁开关1状态 ;<br></br> emb:0x00 = 关门;0x01  =开门; <br></br>庆铃标准 : 0x0：预留 ; 0x1：门关闭 ; 0x2：门打开 ; 0x3：门磁开关1无效  */
    var doorSwitch1 = 0

    /** 门磁开关2状态 ; ; <br></br>庆铃标准 : 0x0：预留 ; 0x1：门关闭 ; 0x2：门打开 ; 0x3：门磁开关1无效  */
    var doorSwitch2 = 0

    /** 压缩机运行状态 ;<br></br> 0x00 = 停止;0x01 = 启动; <br></br>庆铃标准 : 0x0:停止 ; 0x1:启动  */
    var compSts = 0

    /** 机组故障指示 ; ;<br></br> 庆铃标准 : 0x0:无故障，故障灯不点亮 ; 0x1:有故障，故障灯点亮  */
    @Deprecated("")
    var sysFlt = 0

    /** 严重故障指示 ; ;<br></br> 庆铃标准 : 0x0:无严重故障 ; 0x1:发生严重故障 */
    @Deprecated("")
    var sysWarning = 0

    /** 机组卸载完成状态 (预留) ; ; <br></br>庆铃标准 : 0x0:卸载未完成 ; 0x1: 卸载完成  */
    var loadOffSts = 0

    /** 变频器运行状态标志（INV状态） ; ; <br></br>庆铃标准 : 0x0:预留 ; 0x1：正转运行 ; 0x2：反转运行 ; 0x3：停机
     * 0x4：调谐 ; 0x5：故障 ; 6：预留 ; 7：无效值未使用  */
    var freqStatus = 0

    /** DC-DC1状态标志 ; ; <br></br>庆铃标准 : 0x0：无效 ; 0x1：准备就绪 ; 0x2：工作 ; 0x3：停机 ; 0x4：故障 ; 5-6：预留 ; 7：无效值未使用  */
    var dc_dc1_Sts = 0

    /** DC-DC2状态标志 ; ;<br></br> 庆铃标准 : 0x0：无效 ; 0x1：准备就绪 ; 0x2：工作 ; 0x3：停机 ; 0x4：故障 ; 5-6：预留 ; 7：无效值未使用  */
    var dc_dc2_Sts = 0

    /** 新风控制（内外循环）(预留） ; ; <br></br>庆铃标准 : 0x0：OFF 关闭 ; 0x1：ON 开启  */
    var newWindSts = 0

    /** 瞬时功率 ; ;<br></br> 庆铃标准 : 功率，精度0.1 ; 范围0.0到25.5Kw  */
    var power_: Double? = null

    /** 故障码  */
    var errCode = 0

    /** 箱内回风温度（用于表示实际温度） ; ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃  */
    var boxRecoveryAirTemp: Double? = null

    /** 室外温度 ; ; <br></br>庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    var outsideTemp: Double? = null

    /** 除霜温度 ; ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    var defrostTemp: Double? = null

    /** 压缩机排气温度 ; ; <br></br>庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    var compExhaustTemp: Double? = null

    /** 出风温度 ; ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃ */
    var airOutTemp: Double? = null

    /** 高压直流电压 ; ; <br></br>庆铃标准 : 显示精度0.1V、范围0-6553.5V  */
    var highDCVoltage: Double? = null

    /** 高压直流电流 ; ; <br></br>庆铃标准 : 显示精度0.1A、范围0-6553.5A  */
    var highDCCurrent: Double? = null

    /** 控制电压 ; ; <br></br>庆铃标准 : 显示精度0.1V、范围0-36.0V  */
    var ctrlVoltage: Double? = null

    /** 蒸发风机电压 ; ;<br></br> 庆铃标准 : 显示精度0.1V、范围0-6553.5V  */
    var evaporatorVoltage: Double? = null

    /** 冷凝风机电压 ; ;<br></br> 庆铃标准 : 显示精度0.1V、范围0-6553.5V  */
    var condenserVoltage: Double? = null

    /** 制冷剂高压压力 ; ; <br></br>庆铃标准 : 显示精度0.1bar、范围0-6553.5bar  */
    var refrigerantPressure: Double? = null

    /** 机组累计运行时间 ; ;<br></br> 庆铃标准 : 显示精度1h、范围0-65535h  */
    var runningTime: Double? = null

    /** 压缩机转速 ; ;<br></br> 庆铃标准 : 显示精度1rpm、范围0-65535rpm  */
    var compSpeed: Double? = null






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


    /* 新增 软件版本号 */
    /** 字符串格式的软件版本号  */
    var softwareVersionNumStr: String? = null

    /** 逻辑版本第一序号 , 例如 ‘V1.4.0’版本号中的第一位‘1’  */
    var softVersionNum1 = 0

    /** 逻辑版本第二序号 , 例如 ‘V1.4.0’版本号中的第二位‘4’  */
    var softVersionNum2 = 0

    /** 程序顺序版本，在程序逻辑版本未改动的条件下，每次编程修改后，该数值+1 ; 例如 ‘V1.4.0’版本号中的第三位‘0’ */
    var sequenceNum = 0

    /** 软件型号（基本型部分）,例如 例如十进制值58，表示580中的58  */
    var softBaseNum = 0

    /** 软件型号（变型部分）, ,例如 例如十进制值0，表示580中的0  */
    var softModifyNum = 0

    /** 底盘厂家代号 , 例如17表示庆铃  */
    var chassisFactoryNum = 0

    /** 车辆型号 , 底盘规格代码号，该底盘厂家具体的底盘型号，例如1表示庆铃的新轻卡 */
    var vehicleModel = 0

    /** 上装厂家代码,冷机厂家代码。 例如1表示EMB, */
    var cabinFactorNum = 0

    init {
        Log.d(LogTag, "上装对象实例化完成，等待写入数据。上装类型 : " + this.javaClass.simpleName)
    }


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


    companion object {
        private const val LogTag = "EMBCabin"
    }

    override fun copyNew(): EMBCabin {
        return clone()
    }

    override fun clone(): EMBCabin {
        return super.clone() as EMBCabin
    }
}