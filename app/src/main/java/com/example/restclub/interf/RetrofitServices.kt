package com.example.restclub.interf

import com.example.restclub.model.Restaurant
import com.example.restclub.model.Root
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Query

interface RetrofitServices {

    @GET("main_map_restaurants_api")
    fun getStartRestaurantsAPI(): Call<Root>

    @GET("search_results_view_api")
    fun searchAPI(@Query("search") search : String): Call<Root>

    @GET("restaurants_card_api")
    fun getInfoRestaurantAPI(@Query ("rest_id")id:Int):Call<Restaurant>

}