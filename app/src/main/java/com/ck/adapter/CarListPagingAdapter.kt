package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.CarBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemCarListBinding
import com.ck.ui.HomeDiscountViewModel

class CarListPagingAdapter :
    PagingDataAdapter<CarBean, CarListPagingAdapter.ViewHolder>(CarDiffCallback()) {

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
        getItem(position)?.let { holder.bind(it) }
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


