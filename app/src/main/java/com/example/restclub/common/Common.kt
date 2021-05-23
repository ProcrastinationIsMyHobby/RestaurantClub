package com.example.restclub.common

import com.example.restclub.interf.RetrofitServices
import com.example.restclub.retrofit.RetrofitClient

object Common {
    const val BASE_URL = "http://6bceadf3e257.ngrok.io/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}