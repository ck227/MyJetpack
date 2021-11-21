package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentContractBinding
import kotlinx.android.synthetic.main.base_title.view.*

class ContractFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContractBinding.inflate(inflater, container, false)
        binding.titleLayout.tv_title.text = "租车协议"
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

}