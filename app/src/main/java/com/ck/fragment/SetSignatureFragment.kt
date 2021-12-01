package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ck.data.viewmodel.UpdateUserViewModel
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentSetSignatureBinding
import com.ck.util.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*
import java.util.HashMap

@AndroidEntryPoint
class SetSignatureFragment : BaseFragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private lateinit var user: User
    private var isFirstLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSetSignatureBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "设置签名"
        binding.titleLayout.tv_right_text.text = "保存"

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            if (!isFirstLoad) {
                findNavController().navigateUp()
            }
            user = it
            binding.tvSignature.setText(user.autograph)
            isFirstLoad = false
        })

        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        binding.titleLayout.tv_right_text.setOnClickListener {
            val signature = binding.tvSignature.text.toString()
            if (signature.isEmpty()) {
                Toast.makeText(context, "请输入签名", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = user.id
            map["autograph"] = signature
            updateUserViewModel.updateSignature(map)
        }
        updateUserViewModel.updateSignatureResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            if ("0" == it.status) {
                userViewModel.updateSignature(binding.tvSignature.text.toString())
            }
        }
        return binding.root
    }
}