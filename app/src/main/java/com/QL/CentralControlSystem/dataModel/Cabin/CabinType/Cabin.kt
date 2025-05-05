package com.QL.CentralControlSystem.dataModel.Cabin.CabinType

import android.util.Log
import java.io.Serializable

/**
 * Cabin类，所有上装的基类，封装了一些和报文收发有关的函数。
 * <br></br> 基类实现了Serializable 接口，可以作为数据传输
 */
abstract class Cabin : Serializable {
    var isCanDataToInstance = false

    /** 接收报文id  */
    var receiveID // 0x18981824
            : Int? = null

    /** 接受到的8*8的CAN数据矩阵，共64个bit  */
    var data = ByteArray(64) //接收到的数据

    /** 整个报文 ,例如 [ AA,55,0F,C1,   24,18,98,18,  08,   02,4B,55,20,00,00,00,00,   6F, ]  */
    var receiveFrame = ByteArray(18)
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

    /** 得到一段报文 */
    fun compileSendData(sendId: Int): IntArray {
        throw NotImplementedError("该函数还没有实例化")
    }

    companion object {
        private const val LogTag = "Cabin"
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