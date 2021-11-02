package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.MsgBean
import com.ck.fragment.MsgCenterFragmentDirections
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemMsgCenterBinding
import com.ck.ui.MsgCenterViewModel

class MsgCenterPagingAdapter :
    PagingDataAdapter<MsgBean, MsgCenterPagingAdapter.ViewHolder>(MsgDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_msg_center,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val msgBean = getItem(position)
//        (holder as ViewHolder).bind(msgBean)
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ItemMsgCenterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

            binding.setClickListener { view ->
                binding.msgCenter?.let { hello ->
                    val direction =
                        MsgCenterFragmentDirections.actionMessageFragmentToMsgDetailFragment(
                            hello._msgBean
                        )
                    view.findNavController().navigate(direction)
                }
            }
        }

        fun bind(item: MsgBean) {
            with(binding) {
                msgCenter = MsgCenterViewModel(item)
                executePendingBindings()
            }
        }
    }
}

class MsgDiffCallback : DiffUtil.ItemCallback<MsgBean>() {
    override fun areItemsTheSame(oldItem: MsgBean, newItem: MsgBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MsgBean, newItem: MsgBean): Boolean {
        return oldItem == newItem
    }
}