package com.example.seremprepruebatecnica.network

data class Users( val address: Address, val company: Company, val id: Long, val email: String, val name: String, val username: String,
val website: String,  val phone: String)


data class Address(val city: String, val street: String, val suite: String, val zipcode: String, val geo: Geo)
data class Geo(val lat: String, val lng: String)
data class Company(val bs: String, val catchPhrase: String, val name: String,)