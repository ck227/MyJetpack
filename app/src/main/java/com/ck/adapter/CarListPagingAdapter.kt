package com.ck.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.CarBean
import com.ck.fragment.*
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemCarListBinding
import com.ck.ui.HomeDiscountViewModel

class CarListPagingAdapter(private val fragment: Fragment, private val activity: Activity) :
    PagingDataAdapter<CarBean, CarListPagingAdapter.ViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            fragment,
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
        getItem(position)?.let { holder.bind(it) }
    }


    class ViewHolder(
        private val fragment: Fragment,
        private val activity: Activity,
        private val binding: ItemCarListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.let { data ->

                    if (fragment is DisCountFragment) {
                        val direction =
                            DisCountFragmentDirections.actionDiscountFragmentToCarDetailFragment(
                                data.id
                            )
                        Navigation.findNavController(view).navigate(direction)
                    } else if (fragment is SearchResultFragment) {
                        val direction =
                            SearchResultFragmentDirections.actionSearchResultFragmentToCarDetailFragment(
                                data.id
                            )
                        Navigation.findNavController(view).navigate(direction)
                    } else if (fragment is HomeFragment1) {
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
        }

        fun bind(item: CarBean) {
            with(binding) {
                viewModel = HomeDiscountViewModel(item)
                executePendingBindings()
            }
        }
    }
}


