package com.ql.ccs.viewUtil

import android.os.Handler
import android.os.Looper
import android.view.View

/** 共用方法 ， 设置按键延迟不可点击
 * @param delayedTime 你要延迟的时间(毫秒)
 */
fun View.setButtonDelayed(delayedTime: Int = 1000) {
    this.isEnabled = false
    // 延迟一定时间后恢复按钮可点击状态 Looper.getMainLooper()  Looper.prepare() ;
    Handler(Looper.getMainLooper()).postDelayed(
        { this.isEnabled = true },
        delayedTime.toLong()
    ) // 延迟时间为2秒（单位为毫秒，这里设置为2000毫秒）
}