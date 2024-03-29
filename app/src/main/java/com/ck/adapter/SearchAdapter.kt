package com.ck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ck.fragment.SearchFragmentDirections
import com.ck.myjetpack.R
import com.ck.myjetpack.databinding.ItemSearchBinding
import com.ck.ui.SearchViewModel

class SearchAdapter :
    ListAdapter<String, SearchAdapter.ViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setCkListener { view ->
                binding.search?.let { search ->

                    val direction =
                        SearchFragmentDirections.actionSearchFragmentToResultFragment(
                            search.str
                        )
                    view.findNavController().navigate(direction)

                }
            }
        }

        fun bind(item: String) {
            with(binding) {
                search = SearchViewModel(item)
                executePendingBindings()
            }
        }
    }
}

class StringDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}