package com.ql.ccs.dataModel.cabin.enumVar

/**
 * 故障码枚举变量
 */
enum class EMBErrorCode(
    /** 故障名称  */
    val errorName: String,
    /** 故障码  */
    val errorCode: Int,
    /** 故障等级，1级故障不显示（但可查询），2级故障主界面显示，3级故障表示严重故障 , 4 非常严重故障 */
    val errorLevel: Int,
    /** viewId */
    val viewId: Int,
) {
    NOFault("机组正常", 0, 0, MyViewIds.NOFaultId),
    CtrlPowerFlt( "控制电源故障", 1, 4, MyViewIds.CtrlPowerFltId ),
    ReAirTempSensorFlt( "箱内回风温度传感器故障", 2, 2, MyViewIds.ReAirTempSensorFltId ),
    EnvTempSensorFlt( "箱外环境温度传感器故障", 3, 1, MyViewIds.EnvTempSensorFltId ),
    EvaporatorOutWindTempSensorFlt( "蒸发器出风温度传感器故障", 4, 1, MyViewIds.EvaporatorOutWindTempSensorFltId ),
    DefrostTempSensorFlt( "除霜温度传感器故障", 5, 2, MyViewIds.DefrostTempSensorFltId ),
    RefrigerantHighPresFlt( "系统制冷剂高压压力故障", 6, 3, MyViewIds.RefrigerantHighPresFltId ),
    RefrigerantLowPresFlt( "系统制冷剂低压压力故障", 7, 3, MyViewIds.RefrigerantLowPresFltId ),
    CompDischargeTempHighFlt( "压缩机排气温度高故障", 8,  3, MyViewIds.CompDischargeTempHighFltId ),
    InverterFault("变频器故障", 9, 4, MyViewIds.InverterFaultId),
    DC_DCFlt( "DC-DC故障", 11, 4, MyViewIds.DC_DCFltId),
    HighPresSensorFlt("高压压力传感器故障", 12, 2, MyViewIds.HighPresSensorFltId),
    CompExhaustTempSensorFlt("压缩机排气温度传感器故障", 14, 2, MyViewIds.CompExhaustTempSensorFltId),
    AreaATempSensorFlt("箱内A区温度传感器故障", 21, 1, MyViewIds.AreaATempSensorFltId),
    AreaBTempSensorFlt("箱内B区温度传感器故障", 22, 1, MyViewIds.AreaBTempSensorFltId),
    AreaCTempSensorFlt("箱内C区温度传感器故障", 23, 1, MyViewIds.AreaCTempSensorFltId),
    BoxHumiditySensorFlt("箱内湿度传感器故障", 24, 1, MyViewIds.BoxHumiditySensorFltId),
    MainContactorBlockFlt("机组主接触器粘连故障", 30, 3, MyViewIds.MainContactorBlockFltId),
    VehicleVCUNotAllowPowerFault("车辆VCU不许可上电故障", 31, 4, MyViewIds.VehicleVCUNotAllowPowerFaultId),
    TopCtrlDCComFlt("顶控DC通讯故障", 94, 3, MyViewIds.TopCtrlDCComFltId),
    TopCtrlInverterComFlt("顶控INV变频器通讯故障", 95, 3, MyViewIds.TopCtrlInverterComFltId),
    CollisionRadarModuleFlt("防撞雷达模块通讯故障", 96, 1, MyViewIds.CollisionRadarModuleFltId),

    /** 自带远程管理模块RS485I通讯故障  */
    RemoteManagementModuleRS485IComFlt("自带远程管理模块通讯故障", 97, 1, MyViewIds.RemoteManagementModuleRS485IComFltId),
    TopCtrlVehicleVCUComFlt("顶控车辆VCU通讯故障", 98, 3, MyViewIds.TopCtrlVehicleVCUComFltId),
    TopCtrlVehicleCCSComFlt("顶控车辆中控屏通讯故障", 99, 1, MyViewIds.TopCtrlVehicleCCSComFltId),
    TopCtrlPanelComFlt("顶控面板通讯故障", 100, 3, MyViewIds.TopCtrlPanelComFltId);

//    var errTime = ""
//
//    constructor(
//        errorName: String,
//        errorCode: Int,
//        errorLevel: Int,
//        viewId: Int,
//        errTime: String
//    ) : this(errorName, errorCode, errorLevel, viewId) {
//        this.errTime = errTime
//    }

    companion object {
        fun getErrByCode(code: Int): EMBErrorCode? {
            for (fault in values()) {
                if (fault.errorCode == code) {
                    return fault
                }
            }
            return null // If no matching fault code found
        }
    }
}