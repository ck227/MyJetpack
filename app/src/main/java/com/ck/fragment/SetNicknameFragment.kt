package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentSetNickNameBinding
import com.ck.util.UserViewModel
import com.ck.data.viewmodel.UpdateUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.android.synthetic.main.fragment_set_nick_name.view.*
import java.util.*

@AndroidEntryPoint
class SetNicknameFragment : BaseFragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSetNickNameBinding.inflate(inflater, container, false)
        binding.layoutTitle.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.layoutTitle.tv_title.text = "设置昵称"
        binding.layoutTitle.tv_right_text.text = "保存"

        //设置昵称显示(viewModel初始化的时候就去初始化了user，所以这里能直接拿到)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            binding.etNickname.setText(user.nickName)
        })

        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)

        binding.layoutTitle.tv_right_text.setOnClickListener {
            val nickName = binding.etNickname.text.toString()
            if (nickName.isEmpty()) {
                Toast.makeText(context, "请输入昵称", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = user.id
            map["nickName"] = nickName
            updateUserViewModel.updateNickName(map)
        }
        updateUserViewModel.updateNickNameResponse.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            if ("0" == it.status) {
                userViewModel.updateNickName(binding.etNickname.text.toString())
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}