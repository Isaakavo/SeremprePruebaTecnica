package com.example.seremprepruebatecnica

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seremprepruebatecnica.network.Posts
import com.example.seremprepruebatecnica.posts.PostsAdapter
import org.w3c.dom.Text


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Posts>?){
    val adapter = recyclerView.adapter as PostsAdapter
    adapter.submitList(data)
}

@BindingAdapter("title")
fun bindPost(text: TextView, title: String){
    text.text = title
}

@BindingAdapter("body")
fun bindBody(textView: TextView, body: String){
    textView.text = body
}