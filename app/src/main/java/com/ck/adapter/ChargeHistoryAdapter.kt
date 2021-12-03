package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.ChargeHistoryBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemChargeHistoryBinding
import com.ck.ui.ChargeHistoryBind


class ChargeHistoryAdapter :
    ListAdapter<ChargeHistoryBean, ChargeHistoryAdapter.ViewHolder>(HistoryBeanDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_charge_history,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemChargeHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChargeHistoryBean) {
            with(binding) {
                chargeHistory = ChargeHistoryBind(item)
                executePendingBindings()
            }
        }
    }
}

private class HistoryBeanDiffCallback : DiffUtil.ItemCallback<ChargeHistoryBean>() {

    override fun areItemsTheSame(oldItem: ChargeHistoryBean, newItem: ChargeHistoryBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ChargeHistoryBean,
        newItem: ChargeHistoryBean
    ): Boolean {
        return oldItem == newItem
    }
}