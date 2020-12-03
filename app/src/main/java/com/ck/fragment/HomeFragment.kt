package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ck.adapter.MainPagerAdapter
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 *
 * @author ck
 * @date 2020/12/1
 */
class HomeFragment : Fragment() {

    private lateinit var viewPager222: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewPager222 = binding.viewPager
        val bottomNavigationView = binding.bottomNavigationView
        viewPager222.adapter = MainPagerAdapter(this)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        return binding.root
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.navigation_home -> {
                    viewPager222.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_home1 -> {
                    viewPager222.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_home2 -> {
                    viewPager222.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}