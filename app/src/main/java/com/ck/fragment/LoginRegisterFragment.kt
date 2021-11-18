package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ck.adapter.LoginRegisterAdapter
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentLoginRegisterBinding
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.android.synthetic.main.base_transparent_title.view.*

class LoginRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginRegisterBinding.inflate(inflater, container, false)

        binding.titleLayout.setBackListener {
            findNavController().navigateUp()
        }

        binding.viewPager.adapter = LoginRegisterAdapter(this)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.loginText.setTextColor(resources.getColor(if (position == 0) R.color.login_text_color else R.color.white))
                binding.registerText.setTextColor(resources.getColor(if (position == 1) R.color.login_text_color else R.color.white))
                binding.loginLine.visibility = if (position == 0) View.VISIBLE else View.INVISIBLE
                binding.registerLine.visibility =
                    if (position == 1) View.VISIBLE else View.INVISIBLE

            }
        })
        binding.loginTab.setOnClickListener {
            binding.viewPager.setCurrentItem(0, true)
        }

        binding.registerTab.setOnClickListener {
            binding.viewPager.setCurrentItem(1, true)
        }

        return binding.root
    }
}