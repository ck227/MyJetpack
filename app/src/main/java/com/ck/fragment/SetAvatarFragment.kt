package com.ck.fragment

import android.os.Bundle
import android.view.*
import com.ck.myjetpack.databinding.FragmentSetAvatarBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SetAvatarFragment : BottomSheetDialogFragment() {

    private lateinit var myListener: MyListener

    fun initListener(myListener: MyListener) {
        this.myListener = myListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSetAvatarBinding.inflate(inflater, container, false)
        binding.tvCamera.setOnClickListener {
            myListener.openCamera()
        }
        binding.tvGallery.setOnClickListener {
            myListener.openGallery()
        }

        return binding.root
    }

    interface MyListener {
        fun openCamera()
        fun openGallery()
    }
}