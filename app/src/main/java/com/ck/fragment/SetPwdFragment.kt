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
import com.ck.myjetpack.databinding.FragmentSetPwdBinding
import com.ck.util.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*
import java.util.HashMap

@AndroidEntryPoint
class SetPwdFragment : BaseFragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSetPwdBinding.inflate(inflater, container, false)

        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "修改密码"
        binding.titleLayout.tv_right_text.text = "保存"

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            binding.tvPhone.text = user.loginName
        })

        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)

        updateUserViewModel.updatePwdResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            if ("0" == it.status) {
                findNavController().navigateUp()
            }
        }
        binding.titleLayout.tv_right_text.setOnClickListener {
            val oldPwd = binding.etOldPwd.text.toString()
            val newPwd = binding.etNewPwd.text.toString()
            val newPwd2 = binding.etNewPwd2.text.toString()
            if (oldPwd.isEmpty() || newPwd.isEmpty() || newPwd2.isEmpty()) {
                Toast.makeText(context, "请输入密码", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (newPwd != newPwd2) {
                Toast.makeText(context, "密码不一致", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = user.id
            map["oldPwd"] = oldPwd
            map["newPwd"] = newPwd
            map["type"] = "1"
            updateUserViewModel.updatePwd(map)
        }

        return binding.root
    }
}