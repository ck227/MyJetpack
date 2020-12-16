package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ck.adapter.HomeRecyclerViewAdapter
import com.ck.myjetpack.databinding.FragmentHome0Binding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


/**
 * 首页
 * @author ck
 * @date 2020/12/1
 */
@AndroidEntryPoint
class HomeFragment0 : Fragment() {

    private val adapter = HomeRecyclerViewAdapter()
    private var searchJob: Job? = null

    private lateinit var binding: FragmentHome0Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome0Binding.inflate(inflater, container, false)

        return binding.root
    }
}