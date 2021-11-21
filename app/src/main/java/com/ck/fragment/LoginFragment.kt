package com.ck.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ck.data.LoginBean
import com.ck.myjetpack.databinding.FragmentLoginBinding
import com.ck.viewmodels.CarViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {

    private val carViewModel: CarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.setFindPwdListener {
            (parentFragment as LoginRegisterFragment).openFindPwd()
        }
        binding.setCodeLoginListener {
            (parentFragment as LoginRegisterFragment).openCodeLogin()
        }

        binding.setLoginListener {
            if (checkValue(binding)) {
                carViewModel.loginResponse.observe(viewLifecycleOwner) { baseResponse ->
                    Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                    if ("0" == baseResponse.status) {
                        //保存用户名和登录数据
                        viewLifecycleOwner.lifecycleScope.launch {
                            saveUserBean(baseResponse.data)
                        }
                        (parentFragment as LoginRegisterFragment).close()
                    }
                }
                val map: MutableMap<String, String> = HashMap()
                map["loginName"] = binding.etPhone.text.toString()
                map["passWord"] = binding.etPwd.text.toString()
                carViewModel.login(map)
            }

        }
        return binding.root
    }

    private fun checkValue(binding: FragmentLoginBinding): Boolean {
        val phone = binding.etPhone.text.toString()
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "请输入联系方式", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!phone.startsWith("1") || phone.length != 11) {
            Toast.makeText(context, "手机号格式错误", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etPwd.text.toString())) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show()
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
}