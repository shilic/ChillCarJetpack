package com.ql.ccs.can

import android.content.Context
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB1
import com.ql.ccs.can.mcu.McuAdapter
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB2
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB3
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB4
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB5
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB6
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB7
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMB8
import com.ql.ccs.dataModel.cabin.cabinType.coldCabin.EMBCmd
import quickCanResolver.core.CanIo

/**
 * CAN组件初始化，需要在application里边监听呢
 */
class CanComponentInit(private val appContext: Context) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        initCanIo(appContext)
    }
    private fun initCanIo(that : Context) {
        // 1. 初始化兼容层框架
        val canIo = CanIo.getInstance().addAdapter(McuAdapter::class.java)
        // 2. 完成 数据模型的初始绑定
        canIo.manager.addDbcInputInterface { dbcFilePath -> that.assets.open(dbcFilePath) }
        canIo.manager.bind(EMBCmd())
        canIo.manager.bind(EMB1())
        canIo.manager.bind(EMB2())
        canIo.manager.bind(EMB3())
        canIo.manager.bind(EMB4())
        canIo.manager.bind(EMB5())
        canIo.manager.bind(EMB6())
        canIo.manager.bind(EMB7())
        canIo.manager.bind(EMB8())
        Log.d("CanComponentAppObserver","-------------------------------  框架初始化完成  ------------------------------")
    }
}