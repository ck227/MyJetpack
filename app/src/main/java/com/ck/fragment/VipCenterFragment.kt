package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ck.adapter.VipViewPagerAdapter
import com.ck.myjetpack.databinding.FragmentVipCenterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*

@AndroidEntryPoint
class VipCenterFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVipCenterBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "会员中心"
        binding.viewPager.adapter = VipViewPagerAdapter(this)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.lineSilver.visibility = if (position == 0) View.VISIBLE else View.INVISIBLE
                binding.lineGold.visibility = if (position == 1) View.VISIBLE else View.INVISIBLE
                binding.linePlatinum.visibility =
                    if (position == 2) View.VISIBLE else View.INVISIBLE
                binding.lineBlackGold.visibility =
                    if (position == 3) View.VISIBLE else View.INVISIBLE
                super.onPageSelected(position)
            }
        })
        binding.llSilver.setOnClickListener {
            binding.viewPager.setCurrentItem(0, true)
        }
        binding.llGold.setOnClickListener {
            binding.viewPager.setCurrentItem(1, true)
        }
        binding.llPlatinum.setOnClickListener {
            binding.viewPager.setCurrentItem(2, true)
        }
        binding.llBlackGold.setOnClickListener {
            binding.viewPager.setCurrentItem(3, true)
        }
        return binding.root
    }

}

