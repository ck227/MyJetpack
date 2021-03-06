package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.HomeRecyclerViewAdapter
import com.ck.myjetpack.databinding.FragmentHome0Binding
import com.ck.myjetpack.databinding.FragmentTestListBinding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 *
 * @author ck
 * @date 2021/4/25
 */
@AndroidEntryPoint
class TestListFragment : Fragment() {

    private val adapter = HomeRecyclerViewAdapter()
    private var searchJob: Job? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTestListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.testList.adapter = adapter
        getData()
        return binding.root
    }

    private fun getData() {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getHomeData("6").collectLatest {
                adapter.submitData(it)
            }
        }
    }

}