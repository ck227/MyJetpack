package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentSetNickNameBinding
import com.ck.util.UserViewModel
import com.ck.viewmodels.CarViewModel
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.android.synthetic.main.fragment_set_nick_name.view.*
import java.util.HashMap

class SetNicknameFragment : BaseFragment() {

    private val carViewModel: CarViewModel by activityViewModels()

    //    private lateinit var setNickViewModel: SetNicknameViewModel
    private lateinit var userViewModel: UserViewModel
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

        //设置昵称显示
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            binding.etNickname.setText(user.nickName)
        })

//        setNickViewModel = ViewModelProvider(this).get(SetNicknameViewModel::class.java)

//        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        binding.layoutTitle.tv_right_text.setOnClickListener {
            val nickName = binding.etNickname.text.toString()
            if (nickName.isEmpty()) {
                Toast.makeText(context, "请输入昵称", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = user.id
            map["nickName"] = nickName
            carViewModel.updateUserInfo(map)
            userViewModel.updateNickName(nickName)
        }
        carViewModel.updateUserInfoResponse.observe(viewLifecycleOwner) {
            if ("0" == it.status) {
                Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}