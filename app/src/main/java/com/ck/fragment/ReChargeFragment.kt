package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentReChargeBinding

class ReChargeFragment : BaseFragment() {

    private var payType = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReChargeBinding.inflate(inflater, container, false)
        binding.titleLayout.setBackListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.title.text = "在线充值"
        binding.relWechatPay.setOnClickListener {
            isWxPay(binding, true)
        }
        binding.relAliPay.setOnClickListener {
            isWxPay(binding, false)
        }

        return binding.root
    }

    private fun isWxPay(binding: FragmentReChargeBinding, isWxPay: Boolean) {
        payType = if (isWxPay) 1 else 2
        binding.ivWechatPay.setImageResource(if (isWxPay) R.mipmap.online_pay_selected else R.mipmap.online_pay_unselected)
        binding.ivAliPay.setImageResource(if (isWxPay) R.mipmap.online_pay_unselected else R.mipmap.online_pay_selected)
    }
}