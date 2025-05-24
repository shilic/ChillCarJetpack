package com.ql.ccs


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ql.ccs.databinding.ActivityMainBinding
import quickCanResolver.core.CanIo
import quickCanResolver.core.CanListenService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // Binding 类名由布局文件名自动生成
    /** 初始化CAN监听 */
    private val canListenService : CanListenService = CanListenImp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 初始化碎片
        initFragment()
        // 注册回调函数
        CanIo.getInstance().register(canListenService)
    } // onCreate

    override fun onDestroy() {
        super.onDestroy()
        CanIo.getInstance().unRegisterCanListener()
    }


    private fun initFragment() {
        // 1. 获取碎片容器
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // 2. 获取导航控制器
        val navController = navHostFragment.navController
        // 3. 关键步骤：绑定。以下是绑定步骤
        NavigationUI.setupWithNavController(binding.navigationRail, navController) // 将 左侧 Tab 与 NavigationUI 绑定
    }
    companion object{
        private const val LogTag = "MainActivity"
    }

} // MainActivity : AppCompatActivity()