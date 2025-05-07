package com.QL.CentralControlSystem.dataModel.cabin.cabinType

import android.util.Log
import java.io.Serializable

/**
 * Cabin类，所有上装的基类，封装了一些和报文收发有关的函数。
 * <br></br> 基类实现了Serializable 接口，可以作为数据传输
 */
abstract class Cabin : Serializable {
    /** 得到一段报文 */
    fun compileSendData(sendId: Int): IntArray {
        throw NotImplementedError("该函数还没有实例化")
    }

    companion object {
        const val LogTag = "Cabin"
        const val emb_cabinCtrlId = 0x18FF0019

        /** 发送id_CCS7_ID = 0x1898_2418  */
        const val CCS7_ID = 0x18982418
        const val CCS7_ID_ = 0x18982418 //发送指令使用的id
        const val CCS8_ID = 0x18992418
        const val CCS9_ID = 0x189A2418
        const val CCS10_ID = 0x189B2418
        const val CCS11_ID = 0x189C2418
        const val CCS12_ID = 0x189D2418
        const val CCS13_ID = 0x189E2418
        const val CCS14_ID = 0x189F2418
        const val CCS15_ID = 0x18A02418
        const val CCS16_ID = 0x18A12418

        /** 接收ID_Cabin2CCS1 = 0x1898_1824 ; 由网关转成 0x18FFC119 */
        const val Cabin2CCS1_ID = 0x18981824 // 由网关转成 0x18FFC119

        /** 0x18FFC219  */
        const val Cabin2CCS2_ID = 0x18991824 // 0x18FFC219

        /** 0x18FFC319  */
        const val Cabin2CCS3_ID = 0x189A1824 // 0x18FFC319

        /** 0x18FFC419  */
        const val Cabin2CCS4_ID = 0x189B1824 // 0x18FFC419

        /** 0x18FFC519  */
        const val Cabin2CCS5_ID = 0x189C1824 // 0x18FFC519

        /** 0x18FFC619  */
        const val Cabin2CCS6_ID = 0x189D1824 // 0x18FFC619

        /** 0x18FFC719  */
        const val Cabin2CCS7_ID = 0x189E1824 // 0x18FFC719

        /** 0x18FFC819 新增报文，用于记录软件版本号  */
        const val Cabin2CCS8_ID = 0x189F1824 // 0x18FFC819 新增报文，用于记录软件版本号
        const val Cabin2CCS9_ID = 0x18A01824
        const val Cabin2CCS10_ID = 0x18A11824

        /** VCU发送给中控大屏的报文ID = 0x1899_1824 */
        const val VCUtoCCS2_ID = 0x18991824
    }
}