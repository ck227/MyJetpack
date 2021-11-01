package com.ck.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.unit.TextUnit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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
        binding.tvSearch.setOnClickListener {
            if (TextUtils.isEmpty(binding.etContent.text.toString())) {
                Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show()
            } else {
                val direction =
                    SearchFragmentDirections.actionSearchFragmentToResultFragment(
                        binding.etContent.text.toString()
                    )
                findNavController().navigate(direction)
            }
        }

        return binding.root
    }


}