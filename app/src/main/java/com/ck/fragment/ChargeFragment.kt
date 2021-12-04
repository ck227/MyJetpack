package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ck.adapter.ChargeHistoryAdapter
import com.ck.data.viewmodel.UpdateUserViewModel
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentChargeBinding
import com.ck.util.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*

@AndroidEntryPoint
class ChargeFragment : BaseFragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private lateinit var user: User
    private lateinit var chargeHistoryAdapter: ChargeHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChargeBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "在线充值"

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            getChargeHistory(binding)
        })

        chargeHistoryAdapter = ChargeHistoryAdapter()
        binding.recyclerView.adapter = chargeHistoryAdapter

        binding.tvCharge.setOnClickListener {
            findNavController().navigate(ChargeFragmentDirections.actionChargeFragmentToReChargeFragment(user.id))
        }

        return binding.root
    }

    private fun getChargeHistory(binding: FragmentChargeBinding) {
        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        updateUserViewModel.chargeHistoryResponse.observe(viewLifecycleOwner, {
            binding.accountMoney.text = "¥" + it.accountBalance
            binding.vipMoney.text = "¥" + it.memberBalance
            chargeHistoryAdapter.submitList(it.data)

        })
        val map: MutableMap<String, String> = HashMap()
        map["userId"] = user.id
        updateUserViewModel.getChargeHistory(map)
    }


}