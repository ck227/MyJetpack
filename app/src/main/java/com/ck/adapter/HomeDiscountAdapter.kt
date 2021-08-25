package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.CarBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemHomeDiscountBinding
import com.ck.ui.HomeDiscountViewModel


/**
 *
 * @author ck
 * @date 2021/5/25
 */
class HomeDiscountAdapter :

    ListAdapter<CarBean, HomeDiscountAdapter.PlantViewHolder>(HomeBeanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_discount,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlantViewHolder(
        private val binding: ItemHomeDiscountBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
//            binding.setClickListener {
//            }
        }

        fun bind(item: CarBean) {
//            binding.apply {
//                carBean = item
//                executePendingBindings()
//            }

            with(binding) {
                viewModel = HomeDiscountViewModel(item)
                executePendingBindings()
            }
        }
    }
}

private class HomeBeanDiffCallback : DiffUtil.ItemCallback<CarBean>() {

    override fun areItemsTheSame(oldItem: CarBean, newItem: CarBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarBean, newItem: CarBean): Boolean {
        return oldItem == newItem
    }
}