package com.ql.ccs.views.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ql.ccs.databinding.FragmentTopBinding

/**
 * 顶部组件
 */
class TopFragment : Fragment() {
    // 使用可空变量避免泄漏
    private var _binding: FragmentTopBinding? = null
    // 非空断言（仅在生命周期内安全时使用）
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}