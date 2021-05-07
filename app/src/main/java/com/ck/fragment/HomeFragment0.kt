package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.HomeBannerAdapter
import com.ck.data.HomeBean
import com.ck.myjetpack.databinding.FragmentHome0Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


/**
 * 首页
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment0 : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var getBannerJob: Job? = null
    private lateinit var list: ArrayList<HomeBean>
    private lateinit var homeBannerAdapter: HomeBannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome0Binding.inflate(inflater, container, false)
        context ?: return binding.root
        //初始化banner
        list = ArrayList()
        homeBannerAdapter = HomeBannerAdapter(list, this)
        binding.banner.adapter = homeBannerAdapter
        getBanner()
        return binding.root
    }

    private fun getBanner() {
        val map: MutableMap<String, String> = HashMap()
        map["limit"] = "2"
        map["page"] = "1"
        map["carType"] = "2"
        getBannerJob?.cancel()
        getBannerJob = viewLifecycleOwner.lifecycleScope.launch {
            list.addAll(viewModel.getHomeBanner(map).data)
            homeBannerAdapter.notifyDataSetChanged()
        }
    }

}