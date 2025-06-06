package com.ql.ccs.views.myComponent

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.Toast
import com.ql.ccs.databinding.MyTempSetItemBinding
import com.ql.ccs.tool.toStr

class TempSetItem(context: Context, attrs: AttributeSet? = null)  : RelativeLayout(context, attrs){
    private val binding = MyTempSetItemBinding.inflate(LayoutInflater.from(context), this, true)
    /** 设置温度 。初始值 = 0 //paramValue = progress + minValue; */
    var tempValue = 0.0
    /** 进度条范围为 0 到 最大值 。*/
    var progress = 0
    /** 减少数值的事件，在这里调用主函数的方法，发送报文给上装 */
    var onClickLower : (() -> Unit)? = null
    /** 增加数值的事件 ，在这里调用主函数的方法，发送报文给上装*/
    var onClickIncrease : (() -> Unit)? = null
    /* 计算公式： progressLength = (MaxTemp - MinTemp) /  CellValue ，例如范围 -35 到 40 ，分辨率 0.1 ，进度条长度就是750，而不是75 。
    进度条和进度条最大值只能是 大于0 的整型。进度条 progress 每次 加 1 ，实际值 tempValue 加0.1
    例如 范围 30 到 600 ，分辨率 30 ，进度条最大值 20-1=19 。进度条0的时候，实际值为30；19的时候，表示600 。
    progress = (int) ( tempValue - MinTemp ) / CellValue ;
    tempValue = progress * CellValue + MinTemp ;
    progressLength = (int) ((MaxTemp - MinTemp) /  CellValue)
    */

    init {
        //LayoutInflater.from(context).inflate(R.layout.my_temp_set_item, this) //绑定布局
        binding.workItemTempSetLower.setOnClickListener {
            progress -= 1
            // tempValue <= MinTemp 那么  tempValue = MinTemp;
            if (progress <= 0) {
                progress = 0
                Toast.makeText(context, "设定温度已达下限", Toast.LENGTH_SHORT).show()
            }
            tempValue = progress * CellValue + MinTemp
            Log.d(
                LogTag, "你点击了温度设置按钮，进度条最大值 = " +  binding.workItemTempSetProgress.max
                        + ";进度条(参数值)=" + progress + ";进度条(实际值)=" +  binding.workItemTempSetProgress.progress
                        + ";温度值=" + tempValue
            )
            binding.workItemTempSetValueTv.text = tempValue.toStr() //刷新温度显示
            binding.workItemTempSetProgress.setProgress(progress)  //刷新进度条
            // 执行外部点击事件,在这里调用主函数的方法，发送报文给上装
            onClickLower?.invoke()
        }
        binding.workItemTempSetIncrease.setOnClickListener{
            progress += 1
            if (progress >= progressLength) {  //tempValue >= MaxTemp 那么 ， tempValue = MaxTemp;
                progress = progressLength
                Toast.makeText(context, "设定温度已达上限", Toast.LENGTH_SHORT).show()
            }
            tempValue = progress * CellValue + MinTemp
            Log.d(
                LogTag, "你点击了温度设置按钮，进度条最大值 = " +  binding.workItemTempSetProgress.max
                        + ";进度条(参数值)=" + progress + ";进度条(实际值)=" +  binding.workItemTempSetProgress.progress
                        + ";温度值=" + tempValue
            )
            binding.workItemTempSetValueTv.text = tempValue.toStr() //刷新温度显示
            binding.workItemTempSetProgress.setProgress(progress) //刷新进度条
            // 执行外部点击事件,在这里调用主函数的方法，发送报文给上装
            onClickIncrease?.invoke()
        }
        progress = ((tempValue - MinTemp) / CellValue).toInt()
        binding.workItemTempSetProgress.max = progressLength
        binding.workItemTempSetProgress.setProgress(progress)
        binding.workItemTempSetValueTv.text = tempValue.toString()
        Log.d(
            LogTag, "温度设置模块初始化完成，进度条最大值(实际值) = " + binding.workItemTempSetProgress.max
                    + ";进度条(参数值)=" + progress + ";进度条(实际值)=" + binding.workItemTempSetProgress.progress
                    + ";温度值=" + tempValue
        )
    }


    /**  刷新温度设定模块  */
    fun refreshTempSetItem(tempValue: Double) {
        this.tempValue = tempValue
        progress = ((tempValue - MinTemp) / CellValue).toInt()
        if (progress >= progressLength) {
            progress = progressLength
        } else if (progress <= 0) {
            progress = 0
        }
        binding.workItemTempSetProgress.setProgress(progress)
        binding.workItemTempSetValueTv.text  = tempValue.toStr(1)
    }

    companion object {
        private const val LogTag = "温度调节"
        /** 温度最小值 */
        const val MinTemp = -35.0 // -35
        /** 温度最大值 */
        const val MaxTemp = 40.0 // 40
        /** 调节温度的单位值。 */
        const val CellValue = 1.0 // 1

        /** 计算出进度条长度。 */
        var progressLength = ((MaxTemp - MinTemp) / CellValue).toInt()
    }
}