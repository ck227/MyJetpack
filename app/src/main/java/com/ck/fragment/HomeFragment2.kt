package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.NewsPagingAdapter
import com.ck.adapter.holder.ReposLoadStateAdapter
import com.ck.myjetpack.databinding.FragmentHome2Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment2 : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var newsPagingAdapter: NewsPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome2Binding.inflate(inflater, container, false)
        newsPagingAdapter = NewsPagingAdapter()
        binding.newsList.adapter = newsPagingAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { newsPagingAdapter.retry() },
            footer = ReposLoadStateAdapter { newsPagingAdapter.retry() }
        )
        binding.newsList.setHasFixedSize(true)
        getNews()
        return binding.root
    }

    private fun getNews() {
        val map: MutableMap<String, String> = HashMap()
        map["informationType"] = "2"
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getHomeNews2(map).collectLatest {
                newsPagingAdapter.submitData(it)
            }
        }
    }
}