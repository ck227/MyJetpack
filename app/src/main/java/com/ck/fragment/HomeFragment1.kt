package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome1Binding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = CarListAdapter()
        binding.homeList.adapter = adapter
        getData(adapter)
        return binding.root
    }

    private fun getData(adapter: CarListAdapter) {
        val map: MutableMap<String, String> = HashMap()
        map["brandId"] = ""
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitList(viewModel.getCarList(map).data)
            adapter.notifyDataSetChanged()
        }
    }
}