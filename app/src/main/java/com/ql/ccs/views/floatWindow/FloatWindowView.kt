package com.ql.ccs.views.floatWindow

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.ql.ccs.R
import com.ql.ccs.databinding.EmbFloatWindowBinding

/**
 * 悬浮窗的视图
 */
class FloatWindowView (context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    /** 在自定义 View 或 ViewGroup 中绑定布局 */
    private val binding = EmbFloatWindowBinding.inflate(LayoutInflater.from(context), this, true)
//    private fun initView(context: Context) {
//        //绑定布局。有时候出现问题，只需要同步一下就可以了
//        val parentView: View = LayoutInflater.from(context).inflate(R.layout.emb_float_window, this)
//    }

    fun setTemp(temp: String) {
        binding.floatWindowTempTv.text = temp
    }

    fun setCompState(state: String) {
        binding.floatWindowCompStateTv.text = state
    }

    fun refreshView(state: String, temp: String) {
        setTemp(temp)
        setCompState(state)
    }

    companion object {
        var mWidth = 200
        var mHeight = 120
    }
}