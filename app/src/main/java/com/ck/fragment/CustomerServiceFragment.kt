package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentCustomerServiceBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*

@AndroidEntryPoint
class CustomerServiceFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCustomerServiceBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "联系客服"
        return binding.root
    }
}