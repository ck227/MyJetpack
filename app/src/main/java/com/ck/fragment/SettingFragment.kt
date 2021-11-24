package com.ck.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentSettingBinding
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.android.synthetic.main.base_transparent_title.view.iv_back

class SettingFragment : BaseFragment() {

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "设置"
        binding.relAvatar.setOnClickListener {
            val setAvatarFragment = SetAvatarFragment()
            setAvatarFragment.initListener(object : SetAvatarFragment.MyListener {
                override fun openCamera() {
//                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//                        takePictureIntent.resolveActivity(packageManager)?.also {
//                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//                        }
//                    }
                }

                override fun openGallery() {
                    TODO("Not yet implemented")
                }

            })
            setAvatarFragment.show(childFragmentManager, "name")
        }
        return binding.root
    }
}