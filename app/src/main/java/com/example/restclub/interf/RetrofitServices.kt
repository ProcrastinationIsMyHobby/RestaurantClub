package com.example.restclub.interf

import com.example.restclub.model.Root
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Query

interface RetrofitServices {

    @GET("main_map_restaurants_api")
    fun getStartRestaurants(): Call<Root>

    @GET("search_results_view_api")
    fun searchAPI(@Query("search") search : String): Call<Root>

}