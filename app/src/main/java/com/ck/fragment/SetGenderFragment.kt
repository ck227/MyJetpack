package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ck.myjetpack.databinding.FragmentSetGenderBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SetGenderFragment : BottomSheetDialogFragment() {

    private lateinit var myListener: MyListener

    interface MyListener {
        fun selectMale()
        fun selectFemale()
    }

    fun initListener(myListener: MyListener) {
        this.myListener = myListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSetGenderBinding.inflate(inflater, container, false)
        binding.tvMale.setOnClickListener {
            myListener.selectMale()
        }
        binding.tvFemale.setOnClickListener {
            myListener.selectFemale()
        }
        return binding.root
    }
}