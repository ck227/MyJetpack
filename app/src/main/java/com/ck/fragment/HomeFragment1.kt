package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ck.myjetpack.databinding.FragmentHome1Binding


/**
 *
 * @author ck
 * @date 2020/12/1
 */
class HomeFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome1Binding.inflate(inflater, container, false)
        return binding.root
    }
}