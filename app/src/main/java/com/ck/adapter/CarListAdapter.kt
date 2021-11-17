package com.ck.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.CarBean
import com.ck.fragment.MainFragmentDirections
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemCarListBinding
import com.ck.ui.HomeDiscountViewModel

class CarListAdapter(private val activity: Activity) :
    ListAdapter<CarBean, CarListAdapter.ViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            activity,
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
        private val activity: Activity,
        private val binding: ItemCarListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.viewModel?.let { data ->
                    val direction =
                        MainFragmentDirections.actionMainFragmentToCarDetailFragment(
                            data.id
                        )
                    val mainNavController =
                        Navigation.findNavController(activity, R.id.nav_host)
                    mainNavController.navigate(direction)
                }
            }
        }

        fun bind(item: CarBean) {
            with(binding) {
                viewModel = HomeDiscountViewModel(item)
                executePendingBindings()
            }
        }
    }
}


