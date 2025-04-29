package com.QL.CentralControlSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.QL.CentralControlSystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  // Binding 类名由布局文件名自动生成
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 1. 获取碎片容器
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // 2. 获取导航控制器
        val navController = navHostFragment.navController
        // 3. 关键步骤：绑定。以下是绑定步骤 binding.activityBottom findViewById<BottomNavigationView>(R.id.activity_bottom)
        NavigationUI.setupWithNavController(binding.navigationRail, navController) // 将 左侧 Tab 与 NavigationUI 绑定
    }
}