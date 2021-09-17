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
import com.ck.myjetpack.databinding.FragmentSearchResultBinding
import com.ck.viewmodels.CarViewModel
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchResultFragment : BaseFragment() {

    private val args: SearchResultFragmentArgs by navArgs()

    private var searchJob: Job? = null
    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var carAdapter: CarListPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = args.carName
        binding.titleLayout.tv_right_text.visibility = View.GONE

        carAdapter = CarListPagingAdapter()
        binding.homeList.adapter = carAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { carAdapter.retry() },
            footer = ReposLoadStateAdapter { carAdapter.retry() }
        )

        binding.homeList.setHasFixedSize(true)
        getData(args.carName)
        return binding.root
    }

    private fun getData(carName: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            carViewModel.getCars(carName).collectLatest {
                carAdapter.submitData(it)
            }
        }
    }

}