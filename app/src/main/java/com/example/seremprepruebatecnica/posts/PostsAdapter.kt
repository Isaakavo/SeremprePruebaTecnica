package com.example.seremprepruebatecnica.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.seremprepruebatecnica.R
import com.example.seremprepruebatecnica.databinding.PostItemBinding
import com.example.seremprepruebatecnica.network.Posts

class PostsAdapter(private val favoriteClickListener: FavoriteClickListener,
                    private val navigate: NavigateToDetails):
    androidx.recyclerview.widget.ListAdapter<Posts, PostsAdapter.PostsViewHolder>(DiffCallback) {

    class PostsViewHolder(private var binding: PostItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(post: Posts, favoriteClickListener: FavoriteClickListener, navigate: NavigateToDetails){
                binding.post = post
                binding.clickListener = favoriteClickListener
                binding.navigate = navigate
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = getItem(position)

        Log.d("postision", "${post.id}")
        holder.bind(post, favoriteClickListener, navigate)
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

class FavoriteClickListener(val clickListener: (post: Posts) -> Unit){
    fun onClick(post: Posts) = clickListener(post)
}
class NavigateToDetails(val clickListener: (post: Posts) -> Unit){
    fun onClick(post: Posts) = clickListener(post)
}