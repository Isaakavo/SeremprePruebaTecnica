package com.example.seremprepruebatecnica.posts

import android.service.autofill.Validators.not
import android.util.Log
import androidx.lifecycle.*
import com.example.seremprepruebatecnica.network.PostApi
import com.example.seremprepruebatecnica.network.Posts
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {


    //Local variable to get post from the API
    private val _posts = MutableLiveData<List<Posts>>()
    //Variable to put the result in the recyclerView
    val posts: LiveData<List<Posts>>
        get() = _posts

    private val _navigateToDetail = MutableLiveData<Long?>()
    val navigateToDetails: LiveData<Long?>
        get() = _navigateToDetail

    fun navigateToDetails(id: Long){
        _navigateToDetail.value = id
    }

    fun onDetailsNavigated(){
        _navigateToDetail.value = null
    }
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