package com.ql.ccs.views.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ql.ccs.viewModel.CanViewModel
import com.ql.ccs.databinding.FragmentTopBinding

/**
 * 顶部组件
 */
class TopFragment : Fragment() {
    private lateinit var _binding : FragmentTopBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // 使用 DataBinding 绑定布局，事实上 viewBinding 和 dataBinding 使用同一个绑定
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val canViewModel = ViewModelProvider(requireActivity())[CanViewModel::class.java]
        canViewModel.emb1LiveData.observe(viewLifecycleOwner) {
            binding.emb1 = it
        }
        canViewModel.emb3LiveData.observe(viewLifecycleOwner) {
            binding.emb3 = it
        }
    }

    companion object {
    }
}