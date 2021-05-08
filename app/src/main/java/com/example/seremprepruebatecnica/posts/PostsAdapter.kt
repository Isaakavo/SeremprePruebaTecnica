package com.example.seremprepruebatecnica.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.seremprepruebatecnica.databinding.PostItemBinding
import com.example.seremprepruebatecnica.network.Posts

class PostsAdapter: androidx.recyclerview.widget.ListAdapter<Posts, PostsAdapter.PostsViewHolder>(DiffCallback) {

    class PostsViewHolder(private var binding: PostItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(post: Posts){
                binding.post = post
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = getItem(position)

        holder.bind(post)
    }


    object DiffCallback: DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.id == newItem.id
        }

    }
}