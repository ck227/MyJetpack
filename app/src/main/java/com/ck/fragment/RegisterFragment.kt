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
import androidx.lifecycle.ViewModelProvider
import com.ck.data.viewmodel.UpdateUserViewModel
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentRegisterBinding
import com.ck.viewmodels.CarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {

//    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private var agreeContract = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
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
                map["type"] = "1"
                updateUserViewModel.getCode(map)
            }
        }
        binding.setRegisterListener {
            if (checkCode(binding) && checkValue(binding)) {
                updateUserViewModel.registerResponse.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                }
                val map: MutableMap<String, String> = HashMap()
                map["loginName"] = binding.etPhone.text.toString()
                map["passWord"] = binding.etPassword.text.toString()
                map["phoneCode"] = binding.etCode.text.toString()
                updateUserViewModel.register(map)
            }
        }
        binding.setAgreeContractListener {
            agreeContract = !agreeContract
            binding.ivContract.setImageResource(if (agreeContract) R.mipmap.online_order_selected else R.mipmap.online_order_unselected)
        }

        binding.setContractListener {
            (parentFragment as LoginRegisterFragment).openContract()
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
        if (!agreeContract) {
            Toast.makeText(context, "请同意租车协议", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}