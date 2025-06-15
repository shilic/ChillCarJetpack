package com.ql.ccs.views.bindingAdapters

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ql.ccs.R

@Suppress("unused")
object SettingFragBindingAdapters {
    @BindingAdapter("switchSts")
    @JvmStatic
    fun setWorkMode(view: ImageView, signalValue : Int) {
        // 除了0都是开
        view.setImageDrawable(when(signalValue){
            0 -> ContextCompat.getDrawable(view.context, R.drawable.ic_switch_off2)
            else -> ContextCompat.getDrawable(view.context, R.drawable.ic_switch_on2)
        })
    }

}