package com.example.seremprepruebatecnica.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilterViewModel: ViewModel() {

    val _allPosts = MutableLiveData<Boolean>()
    val _favorites = MutableLiveData<Boolean>()


    private val _returntoOverview = MutableLiveData<Boolean>()

    val returntoOverview: LiveData<Boolean>
        get() = _returntoOverview


    fun onReturnToOverview(){
        _returntoOverview.value = true;
    }


    init {
        _returntoOverview.value = false
    }

}