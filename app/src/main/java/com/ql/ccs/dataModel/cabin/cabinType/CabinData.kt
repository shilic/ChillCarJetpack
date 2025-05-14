package com.ql.ccs.dataModel.cabin.cabinType

/**
 * Cabin类，所有上装的基类，封装了一些和报文收发有关的函数。
 * <br></br> 基类实现了Serializable 接口，可以作为数据传输
 */
abstract class CabinData {
    companion object {
        const val dbcTag1 = "EMBCabin"
        const val dbcPath1 = "database_can/BCAN_EMB.dbc"
        const val LogTag = "Cabin"
        const val emb_cabinCtrlId = 0x18FF_0019

        /** 发送id_CCS7_ID = 0x1898_2418  */
        const val CCS7_ID = 0x1898_2418
        const val CCS7_ID_ = 0x189_82418 //发送指令使用的id
        const val CCS8_ID = 0x1899_2418
        const val CCS9_ID = 0x189A_2418
        const val CCS10_ID = 0x189B_2418
        const val CCS11_ID = 0x189C_2418
        const val CCS12_ID = 0x189D_2418
        const val CCS13_ID = 0x189E_2418
        const val CCS14_ID = 0x189F_2418
        const val CCS15_ID = 0x18A0_2418
        const val CCS16_ID = 0x18A1_2418

        /** 0x1898_1824; 空调主要信息和参数，包括设定温度和工作模式 */
        const val Cabin2CCS1_ID = 0x1898_1824 // 0x18FFC119

        /** 0x1899_1824 ，进阶参数，一般不使用  */
        const val Cabin2CCS2_ID = 0x1899_1824 // 0x18FFC219

        /** 0x189A_1824 ，故障代码，以及机组参数，以及选配参数  */
        const val Cabin2CCS3_ID = 0x189A_1824 // 0x18FFC319

        /** 0x189B_1824 ，其他温度参数，如室外温度  */
        const val Cabin2CCS4_ID = 0x189B_1824 // 0x18FFC419

        /** 0x189C_1824 ABC分区温度和控制电压 */
        const val Cabin2CCS5_ID = 0x189C_1824 // 0x18FFC519

        /** 0x189D_1824 电压电流 */
        const val Cabin2CCS6_ID = 0x189D_1824 // 0x18FFC619

        /** 0x189E_1824 变频器状态 */
        const val Cabin2CCS7_ID = 0x189E_1824 // 0x18FFC719

        /** 0x189F_1824 新增报文，用于记录软件版本号  */
        const val Cabin2CCS8_ID = 0x189F_1824 // 0x18FFC819 新增报文，用于记录软件版本号


        const val Cabin2CCS9_ID = 0x18A0_1824
        const val Cabin2CCS10_ID = 0x18A1_1824

        /** VCU发送给中控大屏的报文ID = 0x1899_1824 */
        const val VCUtoCCS2_ID = 0x18991824
    }
}