package com.ql.ccs.views.myComponent

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.ql.ccs.R
import com.ql.ccs.databinding.MyTempSetItemBinding
import com.ql.ccs.tool.toStr

class TempSetItem(context: Context, attrs: AttributeSet? = null)  : RelativeLayout(context, attrs) , View.OnClickListener {
    private val binding = MyTempSetItemBinding.inflate(LayoutInflater.from(context), this, true)
    /** 设置温度 。初始值 = 0 //paramValue = progress + minValue; */
    var tempValue = 0.0
    /** 进度条范围为 0 到 最大值 。*/
    var progress = 0
    /** 减少数值的事件 */
    var onClickLower : (() -> Unit)? = null
    /** 增加数值的事件 */
    var onClickIncrease : (() -> Unit)? = null

    /* progressLength = (MaxTemp - MinTemp) /  CellValue ，例如范围 -35 到 40 ，分辨率 0.1 ，进度条长度就是750，而不是75 。
    进度条和进度条最大值只能是 大于0 的整型。进度条 progress 每次 加 1 ，实际值 tempValue 加0.1
    例如 范围 30 到 600 ，分辨率 30 ，进度条最大值 20-1=19 。进度条0的时候，实际值为30；19的时候，表示600 。
    progress = (int) ( tempValue - MinTemp ) / CellValue ;
    tempValue = progress * CellValue + MinTemp ;
    progressLength = (int) ((MaxTemp - MinTemp) /  CellValue)
    */

//    var tempValueTv: TextView? = null
//    var btn_lower: ImageView? = null
//    var btn_increase: ImageView? = null
//    var progressBar: MyProgressBar? = null


    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.my_temp_set_item, this) //绑定布局
//        btn_lower = findViewById(R.id.work_item_tempSet_lower)
//        btn_increase = findViewById(R.id.work_item_tempSet_increase)
//        progressBar = findViewById(R.id.work_item_tempSet_Progress)
//        tempValueTv = findViewById(R.id.work_item_tempSet_value_tv)

        binding.workItemTempSetLower.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                progress -= 1
                // tempValue <= MinTemp 那么  tempValue = MinTemp;
                if (progress <= 0) {
                    progress = 0
                    Toast.makeText(context, "设定温度已达下限", Toast.LENGTH_SHORT).show()
                }
                tempValue = progress * CellValue + MinTemp
            }

        })
        btn_increase?.setOnClickListener(this)
        progress = ((tempValue - MinTemp) / CellValue).toInt()
        progressBar?.max = progressLength
        progressBar?.progress = progress
        tempValueTv?.setText(tempValue.toString())
        Log.d(
            LogTag, "温度设置模块初始化完成，进度条最大值(实际值) = " + progressBar?.max
                    + ";进度条(参数值)=" + progress + ";进度条(实际值)=" + progressBar?.progress
                    + ";温度值=" + tempValue
        )
    }


    /** 刷新温度设定模块  */
    fun refreshTempSetItem(tempValue: Double) {
        this.tempValue = tempValue
        progress = ((tempValue - MinTemp) / CellValue).toInt()
        if (progress >= progressLength) {
            progress = progressLength
        } else if (progress <= 0) {
            progress = 0
        }
        if (progressBar != null) {
            progressBar!!.progress = progress
            //Log.d(LogTag," 外部程序尝试刷新温度显示,进度条设置成功 ");
        } else {
            Log.d(LogTag, "progressBar == null 刷新温度显示失败!! ")
        }
        if (tempValueTv != null) {
            tempValueTv!!.text = tempValue.toStr(1)
            //Log.d(LogTag," 外部程序尝试刷新温度显示,数值设置成功 ");
        } else {
            Log.d(LogTag, "tempValueTv == null 刷新温度显示失败!! ")
        }
    }

    override fun onClick(view: View) {
        /* 、progress = ( tempValue - MinTemp ) / CellValue ;
         、tempValue = progress * CellValue + MinTemp ; */
        val clickId = view.id
        if (clickId == R.id.work_item_tempSet_lower) {
            //Log.d(LogTag,"温度减一");
            progress = progress - 1
            if (progress <= 0) {  // tempValue <= MinTemp 那么  tempValue = MinTemp;
                progress = 0
                Toast.makeText(context, "设定温度已达下限", Toast.LENGTH_SHORT).show()
            }
            tempValue = progress * CellValue + MinTemp
            //在这里调用主函数的方法，发送报文给上装
//            if (embAc != null){
//                embAc.setTempValueDownReq();
//            }else {
//                Log.d(LogTag,"embAc == null , 温度设定失效");
//            }
        } else if (clickId == R.id.work_item_tempSet_increase) {
            //Log.d(LogTag,"温度加一");
            progress = progress + 1
            if (progress >= progressLength) {  //tempValue >= MaxTemp 那么 ， tempValue = MaxTemp;
                progress = progressLength
                Toast.makeText(context, "设定温度已达上限", Toast.LENGTH_SHORT).show()
            }
            tempValue = progress * CellValue + MinTemp
            //在这里调用主函数的方法，发送报文给上装
//            if (embAc != null){
//                embAc.setTempValueUpReq();
//            }else {
//                Log.d(LogTag,"embAc == null , 温度设定失效");
//            }
        }

        Log.d(
            LogTag, "你点击了温度设置按钮，进度条最大值 = " + progressBar!!.max
                    + ";进度条(参数值)=" + progress + ";进度条(实际值)=" + progressBar!!.progress
                    + ";温度值=" + tempValue
        )
        tempValueTv!!.text = tempValue.toString() //刷新温度显示
        progressBar!!.progress = progress //刷新进度条
        //embAc.embCabin.tempSetValue  = (int) tempValue;  //刷新本地数据  //embAc.localSetTempValue
    }

    companion object {
        private const val LogTag = "温度调节"
        const val MinTemp = -35.0 // -35
        const val MaxTemp = 40.0 // 40
        const val CellValue = 1.0 // 1
        var progressLength = ((MaxTemp - MinTemp) / CellValue).toInt()
    }
}