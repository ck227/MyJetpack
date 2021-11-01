package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ck.myjetpack.databinding.FragmentNewsBinding
import kotlinx.android.synthetic.main.base_title.view.*

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)

        binding.layoutTitle.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.layoutTitle.tv_title.text = "资讯详情"
        

        return binding.root
    }


}