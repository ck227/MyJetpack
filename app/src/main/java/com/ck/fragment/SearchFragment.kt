package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ck.adapter.SearchAdapter
import com.ck.myjetpack.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchAdapter = SearchAdapter()
        binding.searchList.adapter = searchAdapter
        val stringList = listOf("敞篷", "suv", "加长", "运动", "卡宴")
        searchAdapter.submitList(stringList)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }


}