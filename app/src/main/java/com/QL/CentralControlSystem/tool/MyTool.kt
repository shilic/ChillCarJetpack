package com.QL.CentralControlSystem.tool


/**
     * 将 double 设置为一位小数
     * @param dot 保留的小数点位数
     * @return 返回只保留一位小数的字符串
     */
    fun Double.toStr(dot : Int): String {

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
