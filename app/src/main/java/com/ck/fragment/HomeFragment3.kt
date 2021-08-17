package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.HomeNewsAdapter
import com.ck.myjetpack.databinding.FragmentHome2Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.HashMap


/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment3 : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome2Binding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = HomeNewsAdapter()
        binding.newsList.adapter = adapter
        getNews(adapter)
        return binding.root
    }

    private fun getNews(adapter: HomeNewsAdapter) {
        val map: MutableMap<String, String> = HashMap()
        map["informationType"] = "2"
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitList(viewModel.getHomeNews2(map).data)
            adapter.notifyDataSetChanged()
        }
    }
}