package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.CarBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemCarListBinding
import com.ck.ui.HomeDiscountViewModel

class CarListAdapter :
    ListAdapter<CarBean, CarListAdapter.ViewHolder>(CarListDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_car_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemCarListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
//            binding.setClickListener {
//            }
        }

        fun bind(item: CarBean) {
            with(binding) {
                viewModel = HomeDiscountViewModel(item)
                executePendingBindings()
            }
        }
    }
}


private class CarListDiffCallback : DiffUtil.ItemCallback<CarBean>() {
    override fun areItemsTheSame(oldItem: CarBean, newItem: CarBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarBean, newItem: CarBean): Boolean {
        return oldItem == newItem
    }
}


