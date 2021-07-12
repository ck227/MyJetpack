package com.ck.adapter


/**
 *
 * @author ck
 * @date 2021/4/7
 */

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ck.fragment.HomeFragment0
import com.ck.fragment.HomeFragment1
import com.ck.fragment.TestListFragment

const val PAGE_INDEX_0 = 0
const val PAGE_INDEX_1 = 1
const val PAGE_INDEX_2 = 2
const val PAGE_INDEX_3 = 3

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PAGE_INDEX_0 to { HomeFragment0() },
        PAGE_INDEX_1 to { HomeFragment1() },
        PAGE_INDEX_2 to { HomeFragment1() },
        PAGE_INDEX_3 to { HomeFragment1() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}