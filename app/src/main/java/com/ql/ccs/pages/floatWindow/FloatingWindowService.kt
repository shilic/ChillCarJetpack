package com.ql.ccs.pages.floatWindow

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import com.ql.ccs.databinding.EmbFloatWindowBinding

class FloatingWindowService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var binding: EmbFloatWindowBinding
    //private lateinit var floatingView: View
    private var initialX = 0
    private var initialY = 0
    private var initialTouchX = 0f
    private var initialTouchY = 0f

    override fun onCreate() {
        super.onCreate()
        createFloatingWindow()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    private fun createFloatingWindow() {
        // 1. 获取WindowManager实例
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        // 2. 初始化悬浮窗布局
        //floatingView = LayoutInflater.from(this).inflate(R.layout.emb_float_window, null)
        binding = EmbFloatWindowBinding.inflate(LayoutInflater.from(this))

        // 3. 设置窗口参数
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            // type /* 请求悬浮窗权限失败，在这里 将TYPE_PHONE 改为 TYPE_APPLICATION_OVERLAY ，成功*/
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            // flag //WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            // Format  //PixelFormat.TRANSLUCENT
            PixelFormat.RGBA_8888
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 500
            y = 100
        }
        //display = true
        // 4. 添加触摸事件
        binding.root.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 记录初始位置
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    // 计算偏移量
                    params.x = initialX + (event.rawX - initialTouchX).toInt()
                    params.y = initialY + (event.rawY - initialTouchY).toInt()
                    windowManager.updateViewLayout(binding.root, params)
                }
            }
            true
        }

        // 5. 点击事件
        binding.floatWindowBack.setOnClickListener {
            windowManager.removeView(binding.root)
            stopSelf() // 停止服务
        }

        // 6. 添加视图到窗口
        windowManager.addView(binding.root, params)
    }
    override fun onDestroy() {
        super.onDestroy()
        // 移除视图并释放资源
        windowManager.removeView(binding.root)
    }
}