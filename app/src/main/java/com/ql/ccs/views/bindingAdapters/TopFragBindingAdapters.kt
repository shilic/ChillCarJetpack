package com.ql.ccs.views.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ql.ccs.R
import com.ql.ccs.views.myComponent.WorkModeSwitch

/**
 * 数据绑定适配器
 */
@Suppress("unused")
object TopFragBindingAdapters {
    @BindingAdapter("workModeSw")
    @JvmStatic
    fun setWorkMode(workModeSwitch: WorkModeSwitch, signalValue : Int) {
        workModeSwitch.setWorkMode(signalValue)
    }
    @BindingAdapter("defrostStageImg")
    @JvmStatic
    fun setDefrostStageImg(view: ImageView, signalValue : Int) {
        // 除了0之外的状态都是除霜开启
        view.setImageDrawable(when(signalValue) {
            0 -> ContextCompat.getDrawable(view.context, R.drawable.ic_defrost_off2)
            else -> {ContextCompat.getDrawable(view.context, R.drawable.ic_defrost_on2)}
        })
    }
    @BindingAdapter("defrostStageTv")
    @JvmStatic
    fun setDefrostStageTv(view: TextView, signalValue : Int) {
        // 除了0之外的状态都是除霜开启 0 "除霜关闭" 1 "除霜准备" 2 "除霜运行" 3 "滴水"
        view.text = when(signalValue) {
            0 -> "除霜关闭"
            1 -> "除霜准备"
            2 -> "除霜运行"
            3 -> "滴水"
            else -> "除霜关闭"
        }
    }
    @BindingAdapter("sterilizeStsImg")
    @JvmStatic
    fun setSterilizeStsImg(view: ImageView, signalValue : Int) {
        // 0x00  = 关闭 ; 0x01  =  开启
        view.setImageDrawable(when(signalValue) {
            0 -> ContextCompat.getDrawable(view.context, R.drawable.ic_sterilization_off)
            else -> {ContextCompat.getDrawable(view.context, R.drawable.ic_sterilization_on)}
        })
    }
    @BindingAdapter("sterilizeStsTv")
    @JvmStatic
    fun setSterilizeStsTv(view: TextView, signalValue : Int) {
        // 0x00  = 关闭 ; 0x01  =  开启
        view.text = when(signalValue) {
            0 -> "杀菌关闭"
            1 -> "杀菌开启"
            else -> "杀菌关闭"
        }
    }
    @BindingAdapter("errCodeVisible")
    @JvmStatic
    fun setErrCodeVisible(layout: LinearLayout, maxFaultLevel : Int) {
        // 默认为 0 ,故障等级，1级故障不显示（但可查询），2级故障主界面显示，3级故障表示严重故障 , 4 非常严重故障
        layout.visibility = when(maxFaultLevel){
            0 -> View.GONE
            else -> View.VISIBLE
        }
    }
    @BindingAdapter("errCodeImg")
    @JvmStatic
    fun setErrCodeImg(view: ImageView, maxFaultLevel : Int) {
        // 默认为 0 ,  故障等级，1级故障不显示（但可查询），  2级故障主界面显示，  3级故障表示严重故障 ,   4 非常严重故障
        view.setImageDrawable(when(maxFaultLevel) {
            0 -> {
                ContextCompat.getDrawable(view.context, R.drawable.ic_sys_fault_level1)
            }
            1 -> ContextCompat.getDrawable(view.context, R.drawable.ic_sys_fault_level1)
            2 -> ContextCompat.getDrawable(view.context, R.drawable.ic_sys_fault_level2)
            3 -> ContextCompat.getDrawable(view.context, R.drawable.ic_sys_fault_level3)
            4 -> ContextCompat.getDrawable(view.context, R.drawable.ic_sys_fault_level4)
            else -> {ContextCompat.getDrawable(view.context, R.drawable.ic_sys_fault_level1)}
        })
    }
    @BindingAdapter("errCodeTv")
    @JvmStatic
    fun setErrCodeTv(view: TextView, maxFaultLevel : Int) {
        view.text = when(maxFaultLevel) {
            0 -> "无故障"
            1 -> "一级故障启"
            2 -> "二级故障"
            3 -> "三级故障"
            4 -> "四级故障"
            else -> "无故障"
        }
    }


    @BindingAdapter("collisionWarnVisible")
    @JvmStatic
    fun setCollisionWarnVisible(layout: LinearLayout, collisionRadarConfig : Int) {
        // 0:未选配; 1:选配
        layout.visibility = when(collisionRadarConfig){
            0 -> View.GONE
            else -> View.VISIBLE
        }
    }
    @BindingAdapter("collisionWarnImg")
    @JvmStatic
    fun setCollisionWarnImg(view: ImageView, collisionWarningSts : Int) {
        // 0: 不报警 ； 1： 报警
        view.visibility = when(collisionWarningSts){
            0 -> {
                View.GONE
                // 关闭报警
            }
            else -> {
                View.VISIBLE
                // 播放报警
            }
        }
    }
} // object FragTopBindingAdapters