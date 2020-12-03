package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ck.myjetpack.databinding.FragmentHome2Binding


/**
 *
 * @author ck
 * @date 2020/12/1
 */
class HomeFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHome2Binding.inflate(inflater, container, false)
        return binding.root
    }
}