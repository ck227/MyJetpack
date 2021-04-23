package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.HomeRecyclerViewAdapter
import com.ck.myjetpack.databinding.FragmentHome1Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment1 : Fragment() {

    private val adapter = HomeRecyclerViewAdapter()
    private var searchJob: Job? = null
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome1Binding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.homeList.adapter = adapter
        getData()
        return binding.root
    }

    private fun getData() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getHomeProducts().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}