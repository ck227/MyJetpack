package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentAboutUsBinding

class AboutUsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}