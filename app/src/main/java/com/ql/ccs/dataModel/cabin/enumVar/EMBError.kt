package com.ql.ccs.dataModel.cabin.enumVar

import java.io.Serializable

/**
 * 将单个故障枚举封装到单个对象中。
 */
class EMBError(
    code : Int,
    /** 故障时间  */
    var errTime : String = ""
) : Serializable, Cloneable {
    private val embErrorCode: EMBErrorCode
    val viewId: Int
    val errorName: String
    val errorCode: Int
    val errorLevel: Int
    init {
        embErrorCode = EMBErrorCode.getErrByCode(code)
        this.viewId = embErrorCode.viewId
        this.errorName = embErrorCode.errorName
        this.errorCode = embErrorCode.errorCode
        this.errorLevel = embErrorCode.errorLevel
    }
    override fun clone(): EMBError {
        return super.clone() as EMBError
    }
    companion object {
        val noFault : EMBError = EMBError(-1)
    }
}