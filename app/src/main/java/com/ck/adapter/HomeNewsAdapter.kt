package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.NewsBean
import com.ck.fragment.MainFragmentDirections
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemHomeNewsBinding
import com.ck.ui.NewsViewModel

class HomeNewsAdapter :

    ListAdapter<NewsBean, HomeNewsAdapter.ViewHolder>(NewsBeanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(private val binding: ItemHomeNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.news?.let { news ->
                    val direction =
                        MainFragmentDirections.actionMainFragmentToNewsFragment(news.source)
                    it.findNavController().navigate(direction)
                }
            }
        }

        fun bind(item: NewsBean) {
            with(binding) {
                news = NewsViewModel(item)
                executePendingBindings()
            }
        }
    }
}


private class NewsBeanDiffCallback : DiffUtil.ItemCallback<NewsBean>() {

    override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem == newItem
    }
}