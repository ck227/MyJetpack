package com.ck.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ck.data.CarDetailBean
import com.ck.fragment.CarDetailDesFragment0
import com.ck.fragment.CarDetailDesFragment1

class CarDetailDescAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PAGE_INDEX_0 to { CarDetailDesFragment0() },
        PAGE_INDEX_1 to { CarDetailDesFragment1() }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}