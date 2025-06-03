package com.ql.ccs.dataModel.cabin.cabinType.coldCabin

import android.util.Log
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.enumVar.EMBError
import quickCanResolver.core.CanBinding
import quickCanResolver.core.CanCopyable
import quickCanResolver.core.DbcBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 0x189A_1824 ，故障代码，以及机组参数，以及选配参数
 */
@DbcBinding(value = [
    DbcBinding.Dbc(dbcTag = CabinData.dbcTag1, dbcPath = CabinData.dbcPath1)
])
data class EMB3(
    /** ----------故障码。-----<br>
    0x00 = 无故障时 <br>
    0x01 =1控制电源故障<br>
    0x02 =2箱内回风温度传感器故障<br>
    0x03 =3箱外环境温度传感器故障<br>
    0x04 =4蒸发器出风温度传感器故障<br>
    0x05 =5除霜温度传感器故障<br>
    0x06 =6系统制冷剂高压压力故障<br>
    0x07 =7系统制冷剂低压压力故障<br>
    0x08 =8压缩机排气温度高故障<br>
    0x09 =9变频器故障<br>
    0x0B=11 DC-DC故障<br>
    0x0C =12高压压力传感器故障<br>
    0x0E =14压缩机排气温度传感器故障<br>
    0x15 =21箱内A区温度传感器故障<br>
    0x16 =22箱内B区温度传感器故障<br>
    0x17 =23箱内C区温度传感器故障<br>
    0x18 =24箱内湿度传感器故障<br>
    0x1E =30机组主接触器粘连故障<br>
    0x1F =31车辆VCU不许可上电故障<br>
    0x5E =94顶控-DC通讯故障<br>
    0x5F =95顶控-INV通讯故障<br>
    0x60 = 96防撞雷达模块通讯故障<br>
    0x61 =97自带远程管理模块RS485I通讯故障<br>
    0x62 =98顶控-车辆VCU通讯故障<br>
    0x63 =99顶控-车辆中控屏通讯故障<br>
    0x64 =100 顶控-面板通讯故障<br>
    ----------故障码。-----<br>
     * */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_errCode")
    var errCode: Int = 0,
    /** 门磁开关1状态 ;<br></br>
     * emb:0x00 = 关门;0x01  =开门; <br></br>
     * 庆铃标准 : 0x0：预留 ; 0x1：门关闭 ; 0x2：门打开 ; 0x3：门磁开关1无效  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_doorSwitch")
    var doorSwitch1: Int = 0,

    /** 市电接入状态/供电状态 ;<br></br>
     * emb标准:0x00 = 电池供电 ;0x01 = 备电供电   ; <br></br>
     * 庆铃标准 :0x0:预留 ; 0x1: 无市电接入，电池供电 ; 0x2：有市电接入，备电供电 ; 0x3：无效值未使用 <br></br> */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_backupPowerSts")
    var backupPowerSts: Int = 0,
    /** 压缩机运行状态 ;<br></br> 0x00 = 停止;0x01 = 启动; <br></br>庆铃标准 : 0x0:停止 ; 0x1:启动  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_compSts")
    var compSts: Int = 0,
    /** 节能是否开启 ;<br></br>
     * emb: 0x0 = 无效;0x1= 有效 ; <br></br>
     * 庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用   */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_ECOSts")
    var ecoSts: Int = 0,
    /** 远程控制状态 ;<br></br>
     * emb :0x00  =面板优先;0x01 =远程控制优先 ;<br></br>
     * 庆铃标准 :0x0：预留 ; 0x1：OFF 关闭 ; 0x2：ON 开启 ; 0x3：无效值未使用  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_remoteControlSts")
    var remoteControlSts: Int = 0,
    /** 防撞雷达选配 <br></br> 0:未选配; 1:选配 ;  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_collisionRadarConfig")
    var collisionRadarConfig: Int = 0,

    /** 箱内回风温度（用于表示实际温度） ; ;<br></br> 庆铃标准 : 显示精度0.1℃、范围-40～140℃  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_boxRecoveryAirTemp")
    var boxRecoveryAirTemp: Double? = null,
    /** 箱内相对湿度 ;<br></br> 显示精度1%、范围0～100%  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_relativeHumidity")
    var relativeHumidity: Double? = null,


    /** 消毒功能选配  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_sterilizeConfig")
    var sterilizeConfig: Int = 0,
    /** 温区A温度传感器 选配<br></br> 0x0:无效 ; 0x1:有效  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_areaATempSensorConfig")
    var areaATempSensorConfig: Int = 0,
    /** 温区B温度传感器 选配<br></br> 0x0:无效 ; 0x1:有效  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_areaBTempSensorConfig")
    var areaBTempSensorConfig: Int = 0,
    /** 温区C温度传感器 选配<br></br> 0x0:无效 ; 0x1:有效  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_areaCTempSensorConfig")
    var areaCTempSensorConfig: Int = 0,
    /** 箱门开关传感器 选配  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_doorSwitchSensorConfig")
    var doorSwitchSensorConfig: Int = 0,
    /** 中控屏配置(中控屏不处理)  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_CCSConfig")
    var ccsConfig: Int = 0,
    /** VCU通讯配置(中控屏不处理)  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_VCUConfig")
    var vcuConfig: Int = 0,
    /** 自带远程模块配置(中控屏不处理)  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_remoteCtrlConfig")
    var remoteCtrlConfig: Int = 0,

    /** 瞬时功率 ; ;<br></br> 庆铃标准 : 功率，精度0.1 ; 范围0.0到25.5Kw  */
    @CanBinding(messageId = CabinData.Cabin2CCS3_ID, signalTag = "emb_power_")
    var currentPower: Double? = null
) : CanCopyable<EMB3>, Cloneable  {
    /** 当前故障，键表示故障码，值表示故障码枚举  */
    var currentErrorMap = HashMap<Int, EMBError>()
    /** 历史故障，键表示故障码，值表示故障码枚举  */
    var historyErrorMap = HashMap<Int, EMBError>()
    /** 故障缓存，键表示故障码，值表示故障码枚举  */
    var bufferErrorMap = HashMap<Int, EMBError>()
    /** 最高 故障等级，默认为 0 ,故障等级，1级故障不显示（但可查询），2级故障主界面显示，3级故障表示严重故障 , 4 非常严重故障 */
    var maxFaultLevel = 0
    /** 缓存 故障等级  */
    private var bufferErrLevel = 0
    /** 故障管理器,负责处理并分析接受到的故障码 */
    fun manageFaults(){
        manageFaults(errCode)
    }
    /**
     * 故障管理器,负责处理并分析接受到的故障码 , Cabin2CCS3_ID = 0x189A_1824
     * @param errCode 传入当前故障码
     */
    private fun manageFaults(errCode: Int) {
//        if (errCode == 97) { // 屏蔽97 远程通信模块故障
//            return
//        }
        val fault : EMBError
        // errCode != 0x00 如果发生了故障
        if (errCode > 0x00) {
            // 如果故障码没有循环一圈，即表示正在发送故障码。遂向历史故障中添加故障码，向故障缓存中添加故障码
            if (errCode != 0xFF) {
                fault = EMBError(errCode)
                //获取故障等级
                val level = fault.errorLevel
                //如果新的故障等级大于旧的故障等级
                if (level > bufferErrLevel) {
                    //记录最高故障等级到缓存变量。
                    bufferErrLevel = level
                }
                //在这里向故障码添加 故障发生的时间
                // 获取当前日期和时间
                val currentDateTime = LocalDateTime.now()
                // 定义日期格式
                val dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm") // "yyyy-MM-dd HH:mm:ss"
                // 格式化日期为字符串
                val formattedDate = currentDateTime.format(dateFormatter)
                fault.errTime = formattedDate

                historyErrorMap[errCode] = fault
                bufferErrorMap[errCode] = fault
            }
            // errCode ==0xFF , 故障码循环一圈
            else {
                //如果循环一圈。首先,清空当前故障 。然后,将 故障缓存 中的故障码转移到 当前故障 中。最后,清空缓存
                currentErrorMap.clear() //清空 当前故障
                /* 有可能发生 0x00 0xFF循环发的情况，故，在操作前需要判断缓存不为 empty . */
                //使用 putAll() 方法将源 Map 的所有映射关系添加到目标 Map 中之后，再使用 clear() 方法清空源 Map 不会影响目标 Map 中的内容。
                // 这是因为 putAll() 方法只是复制了源 Map 的映射关系，而不是引用。所以，一旦映射关系复制到目标 Map 中，目标 Map 就独立于源 Map。
                //有故障码，并且故障缓存不为空
                if (bufferErrorMap.isNotEmpty()) {
                    //将缓存中的故障 全部 添加到当前故障中
                    currentErrorMap.putAll(bufferErrorMap)
                } // else currentErrorMap.isEmpty() == true ;
                bufferErrorMap.clear() //清空缓存
                maxFaultLevel = bufferErrLevel // 将缓存的故障等级设置为当前故障等级。
                bufferErrLevel = 0 //清空故障缓存，重新设置故障等级为 0 ; 以便于在下次记录最高故障等级的时候重新计算
            }
        }
        //  errCode == 0 没有发生故障。清空 当前故障 ，清空故障缓存。
        else if (errCode == 0x00) {
            Log.d("故障管理器", "故障信息为空")
            //故障等级设置为0
            maxFaultLevel = 0
            bufferErrLevel = 0
            currentErrorMap.clear()
            bufferErrorMap.clear() //清空缓存
        } //没有发生故障
        //currentErrorMap.put(errCode,fault);
        Log.d("故障管理器", "添加故障码 , errCode = $errCode")
    } // manageFaults 故障管理器
    override fun copyNew(): EMB3 {
        return clone()
    }
    override fun clone(): EMB3 {
        return super.clone() as EMB3
    }
} // 0x189A_1824 ，故障代码，以及机组参数，以及选配参数