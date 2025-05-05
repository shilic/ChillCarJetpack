package com.QL.CentralControlSystem.dataModel.Cabin.CabinType.ColdCabin

import com.QL.CentralControlSystem.dataModel.Cabin.CabinType.Cabin

/**
 * 制冷上装，继承自 上装基类
 */
open class ColdCabin : Cabin() {
    /* 发送数据 */
    /** 上装取电请求（发给VCU） ;<br></br> 庆铃标准 : 0x0:预留 ; 0x1:无效;  0x2：有效 ; 0x3:无效值未使用   */
    var ptoHvOn_req = 0

    /** 请求机组工作(复位式)<br></br> emb标准 (复位式) : 0x00  无效 ; 0x01  有效 (无论开关机都发1 ，根据 workMode 的值来改变 );<br></br> 庆铃标准 : 0x0: 预留 ; 0x1:OFF（关机） ; 0x2：ON（开机） ; 0x3: 无效值未使用   */
    var switch_req = 0

    /** 请求机组工作模式 ; <br></br>庆铃标准 :  0x0：预留 ; 0x1：制冷 ; 0x2：制热 ; 0x3: 仅通风 ; 4-14：预留 ; 15：无效值未使用  */
    var workMode_req = 0

    /** 请求新风控制（内外循环）（预留） ;<br></br> 庆铃标准 :  0x0: 预留 ; 0x1:OFF（关机） ; 0x2：ON（开机） ; 0x3: 无效值未使用   */
    var newWind_req = 0

    /** 请求手动除霜 ; <br></br>emb标准 (复位式): 0x00  无效 ; 0x01  有效 ; <br></br>庆铃标准 : 0x0: 预留 ; 0x1:除霜关闭  ; 0x2：除霜开启 ; 0x3: 无效值未使用   */
    var defrost_req = 0

    /** 请求杀菌控制 ; <br></br>emb标准 (复位式): 0x00  无效 ; 0x01  有效 ; <br></br>庆铃标准 : 0x0: 预留 ; 0x1:OFF（关机） ; 0x2：ON（开机） ; 0x3: 无效值未使用   */
    var sterilize_req = 0

    /** 无效标志位 ; <br></br>庆铃标准 : 0x0：该帧报文无效 ; 0x1：该帧报文有效  */
    var invalid_ = 1

    /** 制冷模式 ; <br></br>emb标准 : 0x00  无效 ; 0x01  有效  */
    var coldMode_req = 0

    /** 制热模式 ; <br></br>emb标准 : 0x00  无效 ; 0x01  有效  */
    var warmMode_req = 0

    /** 通风模式 ; <br></br>emb标准 : 0x00  无效 ; 0x01  有效  */
    var fanMode_req = 0
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
    /* 新增报文4个，用于冷王等冷机的控制 */
    /** 开关指令（开关式） ;0x0=无效值 ;0x1=开机;0x2=关机;0x3=预留;  */
    var sw_req2 = 0

    /** 模式请求 ;0x0=无效值 ;0x1=制冷请求;0x2=制热请求; 0x3=通风模式请求; */
    var modeReq2 = 0

    /** 手动除霜（开关式） ;0x0=无效值; 0x1=除霜开启; 0x2=除霜关闭; 0x3=预留 ; */
    var defrost_req2 = 0

    /** 臭氧消毒（开关式） ; 0x0=无效值; 0x1=杀菌开启; 0x2=杀菌关闭; 0x3=预留 ; */
    var sterilize_req2 = 0
}