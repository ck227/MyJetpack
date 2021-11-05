package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ck.adapter.CarListPagingAdapter
import com.ck.adapter.holder.ReposLoadStateAdapter
import com.ck.myjetpack.databinding.FragmentDiscountBinding
import com.ck.viewmodels.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap

@AndroidEntryPoint
class DisCountFragment : BaseFragment() {

    private var searchJob: Job? = null
    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var carAdapter: CarListPagingAdapter
    private val args: DisCountFragmentArgs by navArgs()
    private var isHot = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiscountBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = args.titleName
        isHot = args.isHot

        carAdapter = CarListPagingAdapter()
        binding.carList.adapter = carAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { carAdapter.retry() },
            footer = ReposLoadStateAdapter { carAdapter.retry() }
        )
        binding.carList.setHasFixedSize(true)
        getData()
        return binding.root
    }

    private fun getData() {
        val map: MutableMap<String, String> = HashMap()
        map["carType"] = if (isHot) "1" else "2"
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            carViewModel.getCars(map).collectLatest {
                carAdapter.submitData(it)
            }
        }
    }
}