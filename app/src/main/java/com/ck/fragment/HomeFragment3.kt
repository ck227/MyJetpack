package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.ck.myjetpack.BuildConfig
import com.ck.myjetpack.R
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentHome3Binding
import com.ck.util.OPENTYPE
import com.ck.util.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment3 : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome3Binding.inflate(inflater, container, false)
        binding.ivSetting.setOnClickListener {
            open(OPENTYPE.Setting, true)
        }
        binding.setLoginListener {
            open(OPENTYPE.Login, true)
        }
        binding.ivChargeMoney.setOnClickListener {
            open(OPENTYPE.Charge, true)
        }

        binding.aboutUs.setOnClickListener {
            open(OPENTYPE.AboutUs, false)
        }
        binding.contactUs.setOnClickListener {
            open(OPENTYPE.ContactUs, false)
        }
        //版本号
        binding.versionName.text = getString(R.string.version_name, BuildConfig.VERSION_NAME)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            binding.loginRegister.text = if (it.id.isEmpty()) "登录/注册" else it.loginName
            if (it.id.isEmpty()) {
                Glide.with(this).load(R.mipmap.default_avatar)
                    .into(binding.ivAvatar)
            } else {
                Glide.with(this).load(it.headImg)
                    .placeholder(R.mipmap.default_avatar)
                    .error(R.mipmap.default_avatar)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(binding.ivAvatar)
            }

        })
        return binding.root
    }

    private fun open(type: OPENTYPE, needLogin: Boolean) {
        //如果未登录
        if (needLogin && user.id.isEmpty()) {
            ((parentFragment as NavHostFragment).parentFragment as MainFragment).openLoginRegister()
            return
        }
        when (type) {
            OPENTYPE.Setting ->
                ((parentFragment as NavHostFragment).parentFragment as MainFragment).openCharge()
            OPENTYPE.Charge ->
                ((parentFragment as NavHostFragment).parentFragment as MainFragment).openCharge()
            OPENTYPE.AboutUs ->
                ((parentFragment as NavHostFragment).parentFragment as MainFragment).openAboutUs()
            OPENTYPE.ContactUs ->
                ((parentFragment as NavHostFragment).parentFragment as MainFragment).openContactUs()
            else ->
                return
        }


    }

}