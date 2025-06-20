package com.ql.ccs.views.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ql.ccs.databinding.FragmentSettingBinding
import com.ql.ccs.viewModel.CanViewModel

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    // 非空断言（仅在生命周期内安全时使用）
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val canViewModel = ViewModelProvider(requireActivity())[CanViewModel::class.java]
        canViewModel.emb1LiveData.observe(viewLifecycleOwner) {
            binding.emb1 = it
        }
    }

    companion object {
    }
}