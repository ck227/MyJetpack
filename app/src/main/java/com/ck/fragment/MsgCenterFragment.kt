package com.ck.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ck.adapter.MsgCenterPagingAdapter
import com.ck.adapter.holder.ReposLoadStateAdapter
import com.ck.myjetpack.databinding.FragmentMsgCenterBinding
import com.ck.viewmodels.MsgViewModel
import kotlinx.android.synthetic.main.base_title.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MsgCenterFragment : BaseFragment() {

    private var msgJob: Job? = null
    private val msgViewModel: MsgViewModel by activityViewModels()
    private lateinit var msgAdapter: MsgCenterPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMsgCenterBinding.inflate(inflater, container, false)
        binding.titleLayout.iv_back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.titleLayout.tv_title.text = "消息中心"
        binding.titleLayout.tv_right_text.visibility = View.GONE
        msgAdapter = MsgCenterPagingAdapter()
        binding.msgList.adapter = msgAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { msgAdapter.retry() },
            footer = ReposLoadStateAdapter { msgAdapter.retry() }
        )
        binding.msgList.setHasFixedSize(true)
        getData()
        return binding.root
    }

    private fun getData() {
        msgJob?.cancel()
        msgJob = lifecycleScope.launch {
            msgViewModel.getMsgList().collectLatest {
                msgAdapter.submitData(it)
            }
        }
    }

}