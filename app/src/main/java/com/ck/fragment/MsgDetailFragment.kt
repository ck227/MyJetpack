package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ck.myjetpack.databinding.FragmentMsgDetailBinding
import kotlinx.android.synthetic.main.base_title.view.*

class MsgDetailFragment : BaseFragment() {

    private val args: MsgDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMsgDetailBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "消息详情"
        binding.messageTitle.text = args.msgBane.title
        binding.messageContent.text = args.msgBane.title
        binding.messageTime.text = args.msgBane.saveTime
        return binding.root
    }
}