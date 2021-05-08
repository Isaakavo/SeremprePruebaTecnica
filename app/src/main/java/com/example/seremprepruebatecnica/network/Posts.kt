package com.example.seremprepruebatecnica.network

data class Posts(val id: Long, val  body: String, val  title:String, val userId: Long){
    var isRead = false
    var visited = false;
    var isFavorite = false;
}
