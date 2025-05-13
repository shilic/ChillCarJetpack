package com.ql.ccs.dataModel.cabin.enumVar

/**
 * 机组信息枚举。如果有其他机组，只需要再定义这样一个相似的枚举即可
 */
enum class EMBParam(val eMBParamName: String, val paramValue: String) {
    Model("型号", "E600"), ApplicableBoxVolume(
        "适用厢体容积",
        "16-23m³"
    ),
    TemperatureRange("温度范围", "-25℃~+15℃"), CoolingCapacity1(
        "制冷量 0℃~30℃",
        "5300w"
    ),
    CoolingCapacity2("制冷量 -20℃~10℃", "2800w"), PowerConsumption1(
        "功耗 0℃~30℃",
        "3050w"
    ),
    PowerConsumption2("功耗 -20℃~10℃", "2650w"), DriveMode(
        "驱动方式",
        "纯电动"
    ),
    ControlVoltage("控制电压", "DC24V（16~32V）"), LowVoltagePower(
        "低压用电",
        "DC-DC自行供电"
    ),
    HighVoltageSupply("高压输入", "DC380V-DC750V"), BackupPowerSupply(
        "备电输入",
        "AC220V 标配"
    ),
    Compressor("压缩机", "DTH356（海立变频）"), Refrigerant(
        "制冷剂",
        "R404a"
    ),
    RefrigerantCharge("制冷剂加注量", "2.3kg"), DefrostMethod(
        "除霜方式",
        "热气"
    ),
    HeatingMode("加热方式", "热气（选装）"), ControlMode(
        "控制方式",
        "CAN通讯"
    ),
    CondenserAssembly("总成尺寸 mm", "1125×1420×511"), Weight("冷机重量", "108kg"), CondenserFanNum(
        "冷凝风机数量",
        "2只"
    ),
    CondenserFan("冷凝风机风量", "4000m³/h"), EvaporatorFanNum(
        "蒸发风机数量",
        "2只"
    ),
    EvaporatorFan("蒸发风机风量", "2000m³/h");

}