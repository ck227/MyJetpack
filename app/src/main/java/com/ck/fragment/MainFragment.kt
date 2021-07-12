package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ck.adapter.HomeViewPagerAdapter
import com.ck.adapter.PAGE_INDEX_0
import com.ck.adapter.PAGE_INDEX_1
import com.ck.adapter.PAGE_INDEX_2
import com.ck.adapter.PAGE_INDEX_3
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


/**
 *
 * @author ck
 * @date 2020/12/1
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomeViewPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            PAGE_INDEX_0 -> R.drawable.garden_tab_selector
            PAGE_INDEX_1 -> R.drawable.garden_tab_selector
            PAGE_INDEX_2 -> R.drawable.garden_tab_selector
            PAGE_INDEX_3 -> R.drawable.garden_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PAGE_INDEX_0 -> getString(R.string.home_tab_0_title)
            PAGE_INDEX_1 -> getString(R.string.home_tab_1_title)
            PAGE_INDEX_2 -> getString(R.string.home_tab_2_title)
            PAGE_INDEX_3 -> getString(R.string.home_tab_3_title)
            else -> null
        }
    }
}