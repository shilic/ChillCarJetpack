package com.ql.ccs.views.myLayout

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.ql.ccs.R
import com.ql.ccs.databinding.LayoutWorkModeSwitchBinding
import com.ql.ccs.viewUtil.CustomAlertDialog
import com.ql.ccs.viewUtil.setButtonDelayed

/**
 * 工作模式切换小模块，切换制冷、制热、通风
 */
class WorkModeSwitch (context: Context, attrs: AttributeSet? = null) :  LinearLayout(context, attrs) , View.OnClickListener {
    private val binding = LayoutWorkModeSwitchBinding.inflate(LayoutInflater.from(context), this, true)
    // 定义回调接口
    var onConfirmAction: (() -> Unit)? = null
    var onCancelAction: (() -> Unit)? = null
    /** 刷新工作模式开关的样式为制热  */
    fun setWarmWork() {
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_warm)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
    }

    /** 刷新工作模式开关的样式为制冷  */
    fun setColdWork() {
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_cold)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
    }

    /** 刷新工作模式开关的样式为通风  */
    fun setFanWork() {
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_fan)
    }

    /** 设置界面未选中模式  */
    fun setNotWork() {
        binding.workModeWarmImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeColdImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
        binding.workModeFanImg.setBackgroundResource(R.drawable.rounded_corner_item_unselect)
    }

    /** 设置按键延时不可点击  */
    fun setDelayedEnable() {
        binding.workModeWarmImg.setButtonDelayed()
        binding.workModeColdImg.setButtonDelayed()
        binding.workModeFanImg.setButtonDelayed()
    }

    // 在点击事件中，更改数据，并请求发送一组报文，然后刷新界面显示
    override fun onClick(view: View) {
        // setDelayedEnable()
        val clickId = view.id
        //        if (clickId == R.id.display_workMode_warm){ // 点击制热按键
//            if (embAc.embCabin.warmConfig == 0) { //如果制热功能未选配
//                warmConfigDialog() ;
//            }
//            else if (embAc.embCabin.warmConfig == 1){ //制热功能有选装
//                //在这里刷新 控制模式页面
//                setWarmWork();
//                setDelayedEnable();
//                if (embAc != null){
//                    Log.d(LogTag, " embAc != null ，本地设置为制热，刷新页面的数据显示 ");
//                    embAc.embCabin.workMode  = 2 ;  //本地设置为制热 embAc.localWorkMode
//                    refreshWorkModeSwitch() ;  //刷新 工作状态开关 在两个碎片中的显示
//                    //在这里使用 主活动中的方法发起 制热请求
//                    embAc.warmOnReq();
//                } else {
//                    Log.d(LogTag, " embAc == null 无法执行本地刷新操作");
//                }
//            }
//
//        }
//        else if (clickId == R.id.display_workMode_cold){ // 点击制冷按键
//            //在这里刷新 控制模式页面
//            setColdWork();
//            setDelayedEnable();
//            if (embAc != null){
//                Log.d(LogTag, " embAc != null ，本地设置为制冷，刷新页面的数据显示 ");
//                embAc.embCabin.workMode = 1 ;  //本地设置为制冷 embAc.localWorkMode
//                refreshWorkModeSwitch() ;  //刷新 工作状态开关 在两个碎片中的显示
//                //在这里使用 主活动中的方法发起 制冷请求
//                embAc.coldOnReq();
//            }else {
//                Log.d(LogTag, " embAc == null 无法执行本地刷新操作");
//            }
//        }
//        else if (clickId == R.id.display_workMode_fan){ // 点击通风按键
//            //在这里刷新 控制模式页面
//            setFanWork();
//            setDelayedEnable();
//            if (embAc != null){
//                Log.d(LogTag, " embAc != null ，本地设置为通风，刷新页面的数据显示 ");
//                embAc.embCabin.workMode = 3 ;  //本地设置为通风 embAc.localWorkMode
//                refreshWorkModeSwitch() ;  //刷新 工作状态开关 在两个碎片中的显示
//                //在这里使用 主活动中的方法发起 通风请求
//                embAc.fanOnReq();
//            }else {
//                Log.d(LogTag, " embAc == null 无法执行本地刷新操作");
//            }
//        }
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
        val validContext = context.takeIf { it is Activity && !it.isFinishing } ?: return
        // 弹出提示框
        CustomAlertDialog(validContext, "提醒", "该功能暂时没有配置").show()
    }

    companion object {
        private const val LogTag = "工作模式切换开关"
    }
}