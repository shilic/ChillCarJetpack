package com.ql.ccs.views.myComponent

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.ql.ccs.R
import com.ql.ccs.databinding.MyWorkModeSwitchBinding
import com.ql.ccs.viewUtil.CustomAlertDialog
import com.ql.ccs.viewUtil.setButtonDelayed

/**
 * 工作模式切换小模块，切换制冷、制热、通风
 */
class WorkModeSwitch (context: Context, attrs: AttributeSet? = null) :  LinearLayout(context, attrs) {
    private val binding = MyWorkModeSwitchBinding.inflate(LayoutInflater.from(context), this, true)
    // 在点击事件中，更改数据，并请求发送一组报文，然后刷新界面显示
    /** 设置制冷的事件，在这里发送报文设置状态 */
    var setClodEvent : (()->Unit)? = null
    /** 设置制热的事件，在这里发送报文设置状态 */
    var setWarmEvent : (()->Unit)? = null
    /** 设置通风的事件 ，在这里发送报文设置状态*/
    var setFanEvent : (()->Unit)? = null
    init {
        setNotWork()
        /** 刷新工作模式开关的样式为制冷  */
        binding.workModeColdImg.setOnClickListener {
            setDelayedEnable()
            setColdWork()
            setClodEvent?.invoke()
        }
        /** 刷新工作模式开关的样式为制热  */
        binding.workModeWarmImg.setOnClickListener {
            setDelayedEnable()
            setWarmWork()
            setWarmEvent?.invoke()
        }
        /** 刷新工作模式开关的样式为通风  */
        binding.workModeFanImg.setOnClickListener {
            setDelayedEnable()
            setFanWork()
            setFanEvent?.invoke()
        }
    }

    /**
     * 供外部使用，直接使用信号值设置组件样式 <br>
     * 0x00 = 关闭;  <br>
     * 0x01 = 制冷;  <br>
     * 0x02 = 制热;  <br>
     * 0x03 = 通风; <br>
     */
    fun setWorkMode(signalValue : Int) {
        when(signalValue){
            0 -> setNotWork()
            1 -> setColdWork()
            2 -> setWarmWork()
            3 -> setFanWork()
        }
    }
    private fun setWarmWork(){
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_warm)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
    }
    private fun setColdWork(){
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_cold)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
    }
    private fun setFanWork() {
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_fan)
    }

    /** 设置界面未选中模式  */
    private fun setNotWork() {
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
    }
    /** 设置按键延时不可点击  */
    fun setDelayedEnable(){
        binding.workModeWarmImg.setButtonDelayed()
        binding.workModeColdImg.setButtonDelayed()
        binding.workModeFanImg.setButtonDelayed()
    }
    /** 刷新 工作状态开关 在两个碎片中的显示  */
    private fun refreshWorkModeSwitch() {
//        if (embAc != null){
//            if (embAc.displayBarFrag != null){
//                embAc.displayBarFrag.refreshLocalWorkModeSwitch();  //刷新顶部栏的数据显示
//            }
//            if (embAc.settingFrag != null){
//                embAc.settingFrag.refreshLocalWorkModeSwitch();  //刷新设置页面的 工作模式显示
//            }
//        }
    }

    /** 弹出未选配提示  */
    private fun warmConfigDialog() {
        // 检查上下文有效性
        val validContext = context.takeIf { (it is Activity) && !it.isFinishing } ?: return
        // 弹出提示框
        CustomAlertDialog(validContext, "提醒", "该功能暂时没有配置").show()
    }

    enum class WorkMode(val signalValue : Int) {
        Close(0),
        Cold(1),
        Warm(2),
        Fan(3)
    }
    companion object {
        private const val LogTag = "工作模式切换开关"
    }
}