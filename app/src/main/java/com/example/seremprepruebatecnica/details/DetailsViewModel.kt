package com.example.seremprepruebatecnica.details

import android.util.Log
import androidx.lifecycle.*
import com.example.seremprepruebatecnica.network.PostApi
import com.example.seremprepruebatecnica.network.Users
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel: ViewModel() {

//    private var userList = MutableLiveData<List<Users>>()
    var user = MutableLiveData<Users>()

    fun getUser(id: Long){
        viewModelScope.launch {
            try {
                user.value = PostApi.retrofitService.getUsers(id)
                Log.d("user", "${user.value}")

            }catch (e: Exception){
                Log.e("errr", "${e.printStackTrace()}")
            }
        }
    }
}