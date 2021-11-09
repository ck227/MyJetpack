package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ck.myjetpack.databinding.FragmentCarDetailBinding

class CarDetailFragment : BaseFragment() {

    private val args: CarDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        binding.price.text = args.carId
        return binding.root
    }


}