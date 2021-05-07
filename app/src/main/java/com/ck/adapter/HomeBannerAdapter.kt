package com.ck.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ck.data.HomeBean
import com.ck.fragment.BannerFragment


/**
 *
 * @author ck
 * @date 2021/4/29
 */

const val PAGE_0 = 0
const val PAGE_1 = 1

class HomeBannerAdapter(data: List<HomeBean>, fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val hi: List<HomeBean> = data

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PAGE_0 to { BannerFragment.newInstance(hi[0]) },
        PAGE_1 to { BannerFragment.newInstance(hi[1]) }
    )

    override fun getItemCount(): Int = hi.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}