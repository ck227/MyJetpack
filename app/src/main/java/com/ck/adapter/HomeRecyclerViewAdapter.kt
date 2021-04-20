package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ck.data.HomeBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemHomeFragmentBinding


/**
 *
 * @author ck
 * @date 2020/12/4
 */
class HomeRecyclerViewAdapter :
    PagingDataAdapter<HomeBean, HomeRecyclerViewAdapter.PlantViewHolder>(HomeBeanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            ItemHomeFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val car = getItem(position)
        if (car != null) {
            holder.bind(car)
        }
    }

    class PlantViewHolder(
        private val binding: ItemHomeFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
//                binding.plant?.let { plant ->
//                    navigateToPlant(plant, it)
//                }
            }
        }

//        private fun navigateToPlant(
//            plant: HomeBean,
//            view: View
//        ) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    plant.plantId
//                )
//            view.findNavController().navigate(direction)
//        }

        fun bind(item: HomeBean) {
            binding.apply {
                homeBean = item
                executePendingBindings()
            }
        }
    }


}


private class HomeBeanDiffCallback : DiffUtil.ItemCallback<HomeBean>() {

    override fun areItemsTheSame(oldItem: HomeBean, newItem: HomeBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomeBean, newItem: HomeBean): Boolean {
        return oldItem == newItem
    }
}