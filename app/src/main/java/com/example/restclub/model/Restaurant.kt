package com.example.restclub.model

data class Restaurant (var restaurant_name: String,
                       var restaurant_id:Int,
                       var description_restaurant: String,
                       var average_check_restaurant:Int,
                       var location: String,
                       var dish: String?,
                       var reviews: List<Review>,
                       var rating:Float,
                       var image: List<String>){
}