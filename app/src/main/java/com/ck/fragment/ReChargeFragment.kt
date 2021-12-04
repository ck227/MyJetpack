package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ck.data.viewmodel.UpdateUserViewModel
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentReChargeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReChargeFragment : BaseFragment() {

    private val args: ReChargeFragmentArgs by navArgs()

    private var payType = 2

    private lateinit var updateUserViewModel: UpdateUserViewModel

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

        binding.tvSubmit.setOnClickListener {
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = args.userId
            map["rechargePrice"] = "0.01"
            map["type"] = "app"
            updateUserViewModel.getWxPayInfo(map)
        }
        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        updateUserViewModel.wxPayInfoResponse.observe(viewLifecycleOwner, {
            
        })


        return binding.root
    }

    private fun isWxPay(binding: FragmentReChargeBinding, isWxPay: Boolean) {
        payType = if (isWxPay) 1 else 2
        binding.ivWechatPay.setImageResource(if (isWxPay) R.mipmap.online_pay_selected else R.mipmap.online_pay_unselected)
        binding.ivAliPay.setImageResource(if (isWxPay) R.mipmap.online_pay_unselected else R.mipmap.online_pay_selected)
    }
}