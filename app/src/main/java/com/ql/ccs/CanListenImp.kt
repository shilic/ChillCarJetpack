package com.ql.ccs

import android.util.Log
import quickCanResolver.core.CanListenService
import quickCanResolver.tool.SLCTool

class CanListenImp : CanListenService {
    override fun listened(canId: Int, p1: ByteArray?) {
        Log.d(logTag,"最终，主活动的监听被回调, 被监听的报文ID = ${SLCTool.toHexString(canId)}"  )
    }
    companion object{
        private const val logTag = "CanListenImp"
    }
}