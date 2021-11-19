package com.ck.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.ck.myjetpack.databinding.FragmentRegisterBinding
import com.ck.viewmodels.CarViewModel

class RegisterFragment : BaseFragment() {

    private val carViewModel: CarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.setCodeListener {
            if (checkCode(binding)) {
                carViewModel.getCodeResponse.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                    if ("0" == baseResponse.status) {
                        //倒计时
                        timerStart(binding.tvGetCode)
                    }
                }
                val map: MutableMap<String, String> = HashMap()
                map["phone"] = binding.etPhone.text.toString()
                map["type"] = "1"
                carViewModel.getCode(map)
            }
        }
        binding.setRegisterListener {
            if (checkCode(binding) && checkValue(binding)) {
                carViewModel.registerResponse.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                }
                val map: MutableMap<String, String> = HashMap()
                map["loginName"] = binding.etPhone.text.toString()
                map["passWord"] = binding.etPassword.text.toString()
                map["phoneCode"] = binding.etCode.text.toString()
                carViewModel.register(map)
            }
        }
        return binding.root
    }

    private fun checkCode(binding: FragmentRegisterBinding): Boolean {
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
                counter--
                textView.text = counter.toString() + "秒后重试"
                textView.isEnabled = false
            }

            override fun onFinish() {
                textView.text = "发送验证码"
                textView.isEnabled = true
            }
        }.start()
    }

    private fun checkValue(binding: FragmentRegisterBinding): Boolean {
        val password = binding.etPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show()
            return false
        }
        val code = binding.etCode.text.toString()
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(context, "请输入验证码", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}