package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.ck.adapter.CarListPagingAdapter
import com.ck.adapter.holder.ReposLoadStateAdapter
import com.ck.myjetpack.databinding.FragmentHome1Binding
import com.ck.viewmodels.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap

/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment1 : Fragment() {

    private var searchJob: Job? = null
    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var carAdapter: CarListPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome1Binding.inflate(inflater, container, false)

        carAdapter = CarListPagingAdapter(this, activity as FragmentActivity)

        binding.homeList.adapter = carAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { carAdapter.retry() },
            footer = ReposLoadStateAdapter { carAdapter.retry() }
        )

        binding.homeList.setHasFixedSize(true)
        getData()

        binding.setSearchListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).search()
                }
            }
        }
        
        binding.setCustomerListener {
            if (parentFragment is NavHostFragment) {
                if ((parentFragment as NavHostFragment).parentFragment is MainFragment) {
                    ((parentFragment as NavHostFragment).parentFragment as MainFragment).openCustomerService()
                }
            }
        }

        return binding.root
    }

    private fun getData() {
        val map: MutableMap<String, String> = HashMap()
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            carViewModel.getCars(map).collectLatest {
                carAdapter.submitData(it)
            }
        }
    }
}