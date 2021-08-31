package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ck.adapter.CarListPagingAdapter
import com.ck.myjetpack.databinding.FragmentHome1Binding
import com.ck.viewmodels.CarViewModel
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

    private var searchJob: Job? = null
    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var carAdapter: CarListPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome1Binding.inflate(inflater, container, false)
        carAdapter = CarListPagingAdapter()
        binding.homeList.adapter = carAdapter
        binding.homeList.setHasFixedSize(true)
        getData()

        return binding.root
    }

    private fun getData() {
//        carViewModel.allCars.observe(viewLifecycleOwner) { carResponse ->
//            carAdapter.submitList(carResponse.data)
//        }

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            carViewModel.getCars("").collectLatest {
                carAdapter.submitData(it)
            }
        }

    }
}