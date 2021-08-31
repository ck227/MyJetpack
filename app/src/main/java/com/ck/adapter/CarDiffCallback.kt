package com.ck.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ck.data.CarBean

class CarDiffCallback : DiffUtil.ItemCallback<CarBean>() {
    override fun areItemsTheSame(oldItem: CarBean, newItem: CarBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarBean, newItem: CarBean): Boolean {
        return oldItem == newItem
    }
}