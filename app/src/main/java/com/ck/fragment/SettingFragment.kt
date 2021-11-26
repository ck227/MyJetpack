package com.ck.fragment

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.ck.api.ApiService
import com.ck.myjetpack.R
import com.ck.myjetpack.User
import com.ck.myjetpack.databinding.FragmentSettingBinding
import com.ck.util.UserViewModel
import com.ck.viewmodels.CarViewModel
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.android.synthetic.main.base_transparent_title.view.iv_back
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class SettingFragment : BaseFragment() {

    private val carViewModel: CarViewModel by activityViewModels()
    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User

    private val openCamera = 1
    private val openGallery = 2
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "设置"
        binding.relAvatar.setOnClickListener {
            val setAvatarFragment = SetAvatarFragment()
            setAvatarFragment.initListener(object : SetAvatarFragment.MyListener {
                override fun openCamera() {
                    takePhotoFromCamera()
                    setAvatarFragment.dismiss()
                }

                override fun openGallery() {
                    choosePhotoFromGallery()
                    setAvatarFragment.dismiss()
                }

            })
            setAvatarFragment.show(childFragmentManager, "name")
        }
        binding.tvLogout.setOnClickListener {
            userViewModel.logout()
            findNavController().navigateUp()
        }
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, {
            user = it
            Glide.with(this).load(it.headImg)
                .placeholder(R.mipmap.default_avatar)
                .error(R.mipmap.default_avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(binding.ivAvatar)
        })
        return binding.root
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, openCamera)
    }

    fun choosePhotoFromGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, openGallery)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lateinit var thumbnail: Bitmap
        if (requestCode == openCamera && data != null) {
            thumbnail = data.extras!!.get("data") as Bitmap
        } else if (requestCode == openGallery && data != null) {
            val contentURI = data.data
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        context?.let {
            Glide.with(it).load(thumbnail)
                .placeholder(R.mipmap.default_avatar)
                .error(R.mipmap.default_avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(binding.ivAvatar)
        }
        //上传bitmap
        val file = bitmapToFile(thumbnail)
        val requestFile: RequestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)

        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        carViewModel.uploadPic(requestFile, body)

        carViewModel.uploadPicResponse.observe(viewLifecycleOwner) { uploadPicResponse ->
            userViewModel.updateHeadImg(ApiService.BASE_URL + uploadPicResponse.msg)
            //请求接口更新头像
            val map: MutableMap<String, String> = HashMap()
            map["userId"] = user.id
            map["headImg"] = uploadPicResponse.msg
            carViewModel.updateUserInfo(map)
        }
    }

    private fun bitmapToFile(bitmap: Bitmap): File {
        val wrapper = ContextWrapper(context)
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

}