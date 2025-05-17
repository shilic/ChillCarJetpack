package com.ql.ccs

import android.app.Application
import android.util.Log
import com.ql.ccs.dataModel.cabin.cabinType.CabinData
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB1
import quickCanResolver.core.CanIo

class QLApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initCanIo()
    }
    private fun initCanIo() {
        // 1. 初始化兼容层框架
        val canIo = CanIo.getInstance()
        // 2. 完成 数据模型的初始绑定
        canIo.manager.addDbcInputInterface{this.assets.open(CabinData.dbcPath1)}.bind(EMB1())
        Log.d(logTag,"-------------------------------  框架初始化完成  ------------------------------")
    }
    companion object{
        const val logTag = "QLApplication"
    }
}