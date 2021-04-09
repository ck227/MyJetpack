package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.HomeRecyclerViewAdapter
import com.ck.data.KeyResponse
import com.ck.myjetpack.databinding.FragmentHome0Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * 首页
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment0 : Fragment() {

    private val adapter = HomeRecyclerViewAdapter()
    private var getKeyJob: Job? = null
    private var searchJob: Job? = null
    private lateinit var binding: FragmentHome0Binding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome0Binding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.homeRecyclerView.adapter = adapter
        getKey()
        getData()
        return binding.root
    }

    private fun getKey() {
//        viewModel.currentKeyResult.observe(viewLifecycleOwner, Observer { })
//        val keyObserver = Observer<KeyResponse> { keyResponse ->
//            Toast.makeText(context, keyResponse.ckey, Toast.LENGTH_SHORT).show()
//        }
//        viewModel.currentKeyResult.observe(viewLifecycleOwner, keyObserver);
//        viewModel.getKey()

    }


    private fun getData() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getHomeData("6").collectLatest {
                adapter.submitData(it)
            }
        }
    }

}