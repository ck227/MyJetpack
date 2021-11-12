package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ck.myjetpack.databinding.FragmentCarDetailDesc1Binding

class CarDetailDesFragment1 : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailDesc1Binding.inflate(inflater, container, false)
        return binding.root
    }
}