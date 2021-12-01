package com.ck.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ck.data.LoginBean
import com.ck.data.viewmodel.UpdateUserViewModel
import com.ck.myjetpack.databinding.FragmentCodeLoginBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CodeLoginFragment : BaseFragment() {

    private lateinit var updateUserViewModel: UpdateUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCodeLoginBinding.inflate(inflater, container, false)
        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        binding.titleLayout.title.text = "验证码登录"
        binding.titleLayout.setBackListener {
            close()
        }
        binding.setCodeListener {
            if (checkCode(binding)) {
                updateUserViewModel.getCodeResponse.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                    if ("0" == baseResponse.status) {
                        //倒计时
                        timerStart(binding.tvGetCode)
                    }
                }
                val map: MutableMap<String, String> = HashMap()
                map["phone"] = binding.etPhone.text.toString()
                map["type"] = "3"
                updateUserViewModel.getCode(map)
            }
        }

        binding.setSubmitListener {
            if (checkCode(binding) && checkValue(binding)) {
                updateUserViewModel.loginResponse.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                    if ("0" == baseResponse.status) {
                        //返回
                        //保存用户名和登录数据
                        viewLifecycleOwner.lifecycleScope.launch {
                            saveUserBean(baseResponse.data)
                        }
                        close()
                    }
                }
                val map: MutableMap<String, String> = HashMap()
                map["loginName"] = binding.etPhone.text.toString()
                map["phoneCode"] = binding.etCode.text.toString()
                updateUserViewModel.login(map)
            }
        }

        return binding.root
    }

    private fun checkCode(binding: FragmentCodeLoginBinding): Boolean {
        val phone = binding.etPhone.text.toString()
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "请输入联系方式", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!phone.startsWith("1") || phone.length != 11) {
            Toast.makeText(context, "手机号格式错误", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun timerStart(textView: TextView) {
        var counter = 60
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = counter.toString() + "秒后重试"
                counter--
                textView.isEnabled = false
            }

            override fun onFinish() {
                textView.text = "发送验证码"
                textView.isEnabled = true
            }
        }.start()
    }

    private fun checkValue(binding: FragmentCodeLoginBinding): Boolean {
        val code = binding.etCode.text.toString()
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(context, "请输入验证码", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    //存数据
    private suspend fun saveUserBean(loginBean: LoginBean) {
        context?.dataStore?.edit { settings ->
            val gson = Gson()
            val user = gson.toJson(loginBean)
            settings[userKey] = user
        }
    }

    private fun close() {
        findNavController().navigateUp()
    }
}