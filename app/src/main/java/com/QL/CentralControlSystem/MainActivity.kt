package com.QL.CentralControlSystem


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.QL.CentralControlSystem.dataModel.cabin.cabinType.coldCabin.EMBCabin
import com.QL.CentralControlSystem.databinding.ActivityMainBinding
import com.QL.CentralControlSystem.mcu.McuAdapter
import quickCanResolver.core.CanIo
import quickCanResolver.core.CanListenService
import quickCanResolver.tool.SLCTool

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // Binding 类名由布局文件名自动生成
    // 初始化CAN监听
    private val canListenService : CanListenService = CanListenImp()
    // 1. 初始化兼容层框架
    private val canIo = CanIo.getInstance()

    //var modelClazz = EMBCabin::class.java;
    // 2. 完成 数据模型的初始绑定
    //var oldModel: EMBCabin = canIo.manager.bind(modelClazz)
    val oldModel: EMBCabin = canIo.manager.bind(EMBCabin())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 初始化碎片
        initFragment()

        // 注册回调函数
        canIo.register(McuAdapter::class.java, canListenService)

    } // onCreate

    override fun onDestroy() {
        super.onDestroy()
        canIo.unRegisterCanListener()
    }



    private fun initFragment(){
        // 1. 获取碎片容器
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // 2. 获取导航控制器
        val navController = navHostFragment.navController
        // 3. 关键步骤：绑定。以下是绑定步骤 binding.activityBottom findViewById<BottomNavigationView>(R.id.activity_bottom)
        NavigationUI.setupWithNavController(binding.navigationRail, navController) // 将 左侧 Tab 与 NavigationUI 绑定
    }

    /**
     * 接收报文的回调函数
     */
    class CanListenImp : CanListenService {
        override fun listened(canId: Int, p1: ByteArray?) {
            Log.d(logTag,"最终，主活动的监听被回调, 被监听的报文ID = ${SLCTool.toHexString(canId)}"  )
        }
    }
    companion object{
        private const val logTag = "MainActivity"
    }

} // MainActivity : AppCompatActivity()