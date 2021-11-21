package com.ck.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.FragmentContactUsBinding

class ContactUsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactUsBinding.inflate(inflater, container, false)
        binding.setBackListener {
            findNavController().navigateUp()
        }
        binding.title.text = "联系我们"
        binding.rel24HotLine.setOnClickListener {
            callService()
        }
        binding.relCooperationLine.setOnClickListener {
            callService()
        }
        return binding.root
    }

    private fun callService() {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:" + getString(R.string.contact_us_24_hot_line))
        intent.data = data
        startActivity(intent)
    }
}