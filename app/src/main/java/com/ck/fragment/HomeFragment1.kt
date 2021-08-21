package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.ck.adapter.CarListAdapter
import com.ck.myjetpack.databinding.FragmentHome1Binding
import com.ck.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


/**
 *
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment1 : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var carAdapter: CarListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome1Binding.inflate(inflater, container, false)
        context ?: return binding.root

        carAdapter = CarListAdapter()
        binding.homeList.adapter = carAdapter
        getData()
        return binding.root
    }

    private fun getData() {
        viewModel.allCars.observe(viewLifecycleOwner) { carResponse ->
            carAdapter.submitList(carResponse.data)
            carAdapter.notifyItemRangeInserted(0, carResponse.data.size)
        }
    }
}