package com.QL.CentralControlSystem.Tool;

import android.icu.text.DecimalFormat;

public class MyTool {
    /**
     * 将 double 设置为一位小数
     * @param number 你要输入的double类型数值
     * @return 返回只保留一位小数的字符串
     */
    public static String doubleToStr(double number){
        // 创建 DecimalFormat 对象，并设置保留两位小数的格式 #0.00; 一位小数 #0.0
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        // 将 double 数转换为字符串并保留两位小数
        return decimalFormat.format(number);
    }
}
