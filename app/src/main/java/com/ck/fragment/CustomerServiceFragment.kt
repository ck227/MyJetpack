package com.ck.fragment

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentCustomerServiceBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.base_title.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@AndroidEntryPoint
class CustomerServiceFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCustomerServiceBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "联系客服"
        binding.serviceLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            val data = Uri.parse("tel:" + getString(R.string.contact_us_24_hot_line))
            intent.data = data
            startActivity(intent)
        }
        binding.ivQrCode.setOnLongClickListener {
            saveImageToInternalStorage(R.mipmap.qr_code)
            true
        }
        return binding.root
    }

    // Method to save an image to internal storage
    private fun saveImageToInternalStorage(drawableId: Int): Uri {
        val drawable = context?.let { ContextCompat.getDrawable(it, drawableId) }
        val bitmap = (drawable as BitmapDrawable).bitmap
        val wrapper = ContextWrapper(context)
        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)
        // Create a file to save the image
        file = File(file, "客服微信.jpg")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()

            // 最后通知图库更新
            MediaStore.Images.Media.insertImage(
                requireContext().contentResolver,
                file.absolutePath,
                file.name,
                null
            )
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            intent.data = Uri.parse(file.absolutePath)
            context?.sendBroadcast(intent)
            Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "保存失败", Toast.LENGTH_LONG).show()
        }
        return Uri.parse(file.absolutePath)
    }
}