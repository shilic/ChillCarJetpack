package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import android.util.Log
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.enumVar.EMBError
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * EMB上装，用于接收数据
 */
@DbcBinding(value = [
        DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
class EMBCabin : CanCopyable<EMBCabin>, Cloneable  {
    /* ===================================== 接收数据 ========================================= */
    /** 温区A设定温度  ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃  */
    @Deprecated("协议未定义，不使用该值")
    var areaASetTemp = 0.0

    /** 温区B设定温度  ; <br></br>庆铃标准 : 显示精度0.1℃、范围-40～140℃  */
    @Deprecated("协议未定义，不使用该值")
    var areaBSetTemp = 0.0

    /** 温区C设定温度  <br></br>显示精度0.1℃、范围-40～140℃  */
    @Deprecated("协议未定义，不使用该值")
    var areaCSetTemp = 0.0

    /** 机组工作状态 ; ; <br></br>庆铃标准 : 0x0:预留 ; 0x1:关机 ; 0x2:开机 ; 0x3:无效值未使用  */
    @Deprecated("协议未定义，不使用该值")
    var workSwitchSts = 0
    /** 机组除霜状态 ; ; <br></br>庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用  */
    @Deprecated("协议未定义，不使用该值")
    var defrostSts = 0


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
    /* 6月7日 ，新增 10个 设备选配 报文 Config */
    /** 制热功能使能 <br></br> 0x0 = 未使能 ； 0x1 = 使能  */
    var warmConfig = 0


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