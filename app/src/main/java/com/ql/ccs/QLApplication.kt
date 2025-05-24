package com.ql.ccs

import android.app.Application
import android.util.Log
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB1
import com.ql.ccs.mcu.McuAdapter
import quickCanResolver.core.CanIo

class QLApplication : Application() {
    val that = this
    override fun onCreate() {
        super.onCreate()
        initCanIo()
    }
    private fun initCanIo() {
        // 1. 初始化兼容层框架
        val canIo = CanIo.getInstance().addAdapter(McuAdapter::class.java)
        // 2. 完成 数据模型的初始绑定
        canIo.manager.addDbcInputInterface { dbcFilePath -> that.assets.open(dbcFilePath) }
        canIo.manager.bind(EMB1())
        Log.d(logTag,"-------------------------------  框架初始化完成  ------------------------------")
    }
    companion object{
        const val logTag = "QLApplication"
    }
}