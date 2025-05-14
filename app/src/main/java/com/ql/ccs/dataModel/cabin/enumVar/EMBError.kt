package com.ql.ccs.dataModel.cabin.enumVar

import java.io.Serializable

/**
 * 将单个故障枚举封装到单个对象中。
 */
class EMBError(
    code : Int,
    /** 故障时间  */
    var errTime : String = ""
) : Serializable {
    private val embErrorCode: EMBErrorCode
    val viewId: Int
        get() = embErrorCode.viewId
    val errorName: String
        get() = embErrorCode.errorName
    val errorCode: Int
        get() = embErrorCode.errorCode
    val errorLevel: Int
        get() = embErrorCode.errorLevel
    init {
        embErrorCode = EMBErrorCode.getErrByCode(code)
    }
    companion object {
        val noFault: EMBError
            get() {
                return EMBError(-1)
            }
    }
}