package com.QL.CentralControlSystem.dataModel.Cabin.EnumVar

import java.io.Serializable

/**
 * 将单个故障枚举封装到单个对象中。
 */
class EMBError private constructor() : Serializable {
    /** 故障时间  */
    var errTime = ""
    var embErrorCode: EMBErrorCode? = null
    val viewId: Int
        get() = embErrorCode!!.viewId
    val errorName: String
        get() = embErrorCode!!.errorName
    val errorCode: Int
        get() = embErrorCode!!.errorCode
    val errorLevel: Int
        get() = embErrorCode!!.errorLevel

    companion object {
        fun getErrByCode(code: Int): EMBError? {
            val embError = EMBError()
            embError.embErrorCode = EMBErrorCode.getErrByCode(code)
            return if (embError.embErrorCode == null) {
                null
            } else embError
        }

        val noFault: EMBError
            get() {
                val embError = EMBError()
                embError.embErrorCode = EMBErrorCode.NOFault
                return embError
            }
    }
}