package com.ql.ccs


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ql.ccs.can.CanComponent
import com.ql.ccs.viewModel.CanViewModel
import com.ql.ccs.databinding.ActivityMainBinding
import quickCanResolver.core.CanIo

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var canViewModel : CanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()
        canViewModel = ViewModelProvider(this)[CanViewModel::class.java]
        // 注册Lifecycle监听
        lifecycle.addObserver(CanComponent(canViewModel))
        //CanIo.getInstance().register{canId, data8 -> // TODO("这里监听到最新数据，根据不同 canId 使用CanIo.Manager().createNewModel(clazzName) 即可拿取最新解析好的数据模型") }
    } // onCreate

    override fun onDestroy() {
        super.onDestroy()
        //CanIo.getInstance().unRegisterCanListener()
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