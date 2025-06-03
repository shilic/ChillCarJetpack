package com.ql.ccs.can

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ql.ccs.viewModel.CanViewModel
import quickCanResolver.core.CanIo


class CanComponent(private val viewModel: CanViewModel) : DefaultLifecycleObserver {
    init {
        Log.d("CanComponent","CAN生命周期组件， 初始化完成")
    }
    /* // @OnLifecycleEvent 注解会存在反射的性能开销，故官方弃用了该组件，改用了 LifecycleEventObserver 接口 ，或者 DefaultLifecycleObserver 接口更方便 */
    override fun onCreate(owner: LifecycleOwner) {
        CanIo.getInstance().register { canId, data8 ->
            // 将数据转发给ViewModel
            viewModel.updateFromCan(canId, data8)
        }
    }
    override fun onDestroy(owner: LifecycleOwner) {
        CanIo.getInstance().unRegisterCanListener()
        owner.lifecycle.removeObserver(this)
    }
}