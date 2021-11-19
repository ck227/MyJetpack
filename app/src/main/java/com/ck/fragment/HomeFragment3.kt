package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.ck.data.LoginBean
import com.ck.myjetpack.databinding.FragmentHome3Binding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome3Binding.inflate(inflater, container, false)

        binding.setLoginListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openLoginRegister()
                }
            }
        }

        getUserName(binding)
        return binding.root
    }

    private fun getUserName(binding: FragmentHome3Binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            val userKeyFlow: Flow<String> = requireContext().dataStore.data
                .map { preferences ->
                    preferences[userKey] ?: ""
                }
            userKeyFlow.collect { json ->
                val gson = Gson()
                val loginBean = gson.fromJson(json, LoginBean::class.java)
                loginBean?.let {
                    binding.loginRegister.text = loginBean.nickName
                }
            }
        }
    }


}