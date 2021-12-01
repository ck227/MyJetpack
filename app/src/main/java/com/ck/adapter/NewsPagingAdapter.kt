package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ck.data.NewsBean
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemHomeNewsBinding
import com.ck.ui.NewsViewModel

class NewsPagingAdapter :
    PagingDataAdapter<NewsBean, NewsPagingAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_news,
                parent,
                false
            )
        )
    }

    class ViewHolder(
        private val binding: ItemHomeNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

            binding.setClickListener { view ->
                binding.news?.let { hello ->
//                    val direction =
//                        MsgCenterFragmentDirections.actionMessageFragmentToMsgDetailFragment(
//                            hello.source
//                        )
//                    view.findNavController().navigate(direction)
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

class NewsDiffCallback : DiffUtil.ItemCallback<NewsBean>() {
    override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem == newItem
    }
}