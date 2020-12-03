package com.ck.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ck.fragment.HomeFragment0
import com.ck.fragment.HomeFragment1
import com.ck.fragment.HomeFragment2


/**
 *
 * @author ck
 * @date 2020/12/3
 */

const val HOME0_PAGE_INDEX = 0
const val HOME1_PAGE_INDEX = 1
const val HOME2_PAGE_INDEX = 2

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME0_PAGE_INDEX to { HomeFragment0() },
        HOME1_PAGE_INDEX to { HomeFragment1() },
        HOME2_PAGE_INDEX to { HomeFragment2() }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}