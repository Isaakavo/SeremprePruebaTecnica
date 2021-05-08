package com.example.seremprepruebatecnica.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seremprepruebatecnica.network.PostApi
import com.example.seremprepruebatecnica.network.Posts
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {


    //Local variable to get post from the API
    private val _posts = MutableLiveData<List<Posts>>()
    //Variable to put the result in the recyclerView
    val posts: LiveData<List<Posts>>
        get() = _posts

    init{
        getPosts()
    }




    private fun getPosts(){
        viewModelScope.launch {
            try{
                _posts.value = PostApi.retrofitService.getPosts()
            }catch (e: Exception){
                _posts.value = ArrayList()
            }
        }
    }
}