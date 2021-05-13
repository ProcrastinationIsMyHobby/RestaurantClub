package com.example.restclub.interf

import com.example.restclub.model.Root
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {

    @GET("main_map_restaurants_api")
    fun getStartRestaurants(): Call<Root>
}