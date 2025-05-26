package com.ql.ccs.views.myComponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ql.ccs.databinding.MyProgressBarBinding

/**
 * 该组件是自定义的一个进度条组件
 */
class MyProgressBar (context: Context?, attrs: AttributeSet? = null): LinearLayout (context, attrs) {
    private val binding = MyProgressBarBinding.inflate(LayoutInflater.from(context), this, true)
    /** 进度条最大长度 */
    private var maxLength = 100
    /** 仅供外部读取进度条试用，设置进度条请使用 setProgress方法 */
    var progress = 0
        private set

    /** 组件的宽度 */
    private var width = 0
    /**  组件的高度 */
    private var height = 0
    /** 完成区域的宽度 */
    private var displayWeight = 0f
    /** 未完成区域的宽度 */
    private var unDisplayWeight = 100f

    /** 供外部使用，设置最大长度 */
    var max: Int
        get() = maxLength
        set(maxLength) {
            this.maxLength = maxLength
            checkProgress()
        }
    /** 供外部使用，设置组件宽度 */
    var progressWidth: Int
        get() = width
        set(width) {
            this.width = width
            val containerParams = binding.myProgressBarContainer.layoutParams as LayoutParams
            containerParams.width = width
            binding.myProgressBarContainer.layoutParams = containerParams
        }
    /** 供外部使用，设置组件高度 */
    var progressHeight: Int
        get() = height
        set(height) {
            this.height = height
            val containerParams = binding.myProgressBarContainer.layoutParams as LayoutParams
            containerParams.height = height
            binding.myProgressBarContainer.layoutParams = containerParams
        }
    init {
        val layoutParams =binding.myProgressBarUnDisplay .layoutParams as LayoutParams
        unDisplayWeight = layoutParams.weight
    }
    /** 供外部使用 , 用于设置进度条长度*/
    fun setProgress(progressValue : Int) {
        this.progress = progressValue
        checkProgress()
    }
    /** 检查进度条参数，并设置进度。 */
    private fun checkProgress() {
        if (maxLength <= 2) {
            maxLength = 100
        }
        if (progress <= 0) {
            progress = 0
        }
        //如果进度条的值大于最大值
        if (progress >= maxLength) {
            progress = 1
            setMaxPro()
        } else {
            //一般参数
            setDefault()
        }
    }
    /** 一般参数设置 */
    private fun setDefault() {
        // 未完成区域 设置为 100
        val unDisplayParams = binding.myProgressBarUnDisplay.layoutParams as LayoutParams
        unDisplayParams.weight = 100f // 设置为 100
        binding.myProgressBarUnDisplay.layoutParams = unDisplayParams
        // 根据当前进度 progress ，计算完成区域的宽度 。 完成区域宽度/进度条长度 = 未完成区域宽度/未完成的进度(也就是maxLength - progress)
        displayWeight = unDisplayWeight * progress.toFloat() / (maxLength - progress)
        val displayParams =  binding.myProgressBarDisplay.layoutParams as LayoutParams
        displayParams.weight = displayWeight
        binding.myProgressBarDisplay.layoutParams = displayParams
    }
    /** 设置最大长度 */
    private fun setMaxPro() {
        // 未完成区域 设置为 0
        val unDisplayParams = binding.myProgressBarUnDisplay .layoutParams as LayoutParams
        unDisplayParams.weight = 0f
        binding.myProgressBarUnDisplay.layoutParams = unDisplayParams
        // 完成区域 设置为 100
        val displayParams = binding.myProgressBarDisplay.layoutParams as LayoutParams
        displayParams.weight = 100f
        binding.myProgressBarDisplay.layoutParams = displayParams
    }


}