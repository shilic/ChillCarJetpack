package com.ql.ccs.tool

import android.content.Context
import java.io.IOException
import java.io.InputStream


/**
* 将 double 设置为一位小数
* @param dot 保留的小数点位数
* @return 返回只保留一位小数的字符串
*/
fun Double.toStr(dot : Int = 1): String {
    val patternSb = StringBuilder()
    patternSb.append("0.")
    val dot1 : Int = if (dot <= 0) {
        1
    }else{
        dot
    }
    for (i in 0 until dot1) {
        patternSb.append("0")
    }

    val pattern = patternSb.toString()
    val decimalFormat = java.text.DecimalFormat(pattern)
    return decimalFormat.format(this)
}

/**
 * 解析一个位于资产目录下的文件，并且自动关闭文件
 */
fun parseAssetFile(context: Context, assetFileName: String?, parseStream : (inputStream : InputStream)->Unit) {
    try {
        val inputStream = context.assets.open(assetFileName!!)
        parseStream(inputStream) // 调用改造后的方法
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
/**
 * 扩展函数：将每个 Byte 转换为无符号 Int
  */
fun ByteArray.toIntArray(): IntArray {
    return IntArray(this.size) { index ->
        this[index].toInt() and 0xFF // 消除符号扩展
    }
}
