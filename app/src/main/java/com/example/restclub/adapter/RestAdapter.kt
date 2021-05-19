package com.example.restclub.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.restclub.MainActivity
import com.example.restclub.R
import com.example.restclub.RestaurantActivity
import com.example.restclub.common.Common
import com.example.restclub.model.Restaurant
import com.example.restclub.model.Root
import com.example.restclub.pictrans.TransPic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*
import java.security.AccessController.getContext


class MyAdapter(private val context: Context,private val root: Root?):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val restList: List<Restaurant> = root!!.data

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.card_image
        val txtTitle: TextView = itemView.txtTitle
        val averageCheck: TextView = itemView.txtAverageCheck
        val txtLocation: TextView = itemView.txtLocation

        fun bind(listItem: Restaurant) {

            itemView.setOnClickListener {
                TODO("Make magick")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = restList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = restList[position]

        if(listItem.image.isNotEmpty()) {
            Picasso.get().load(Common.BASE_URL + "media/" + listItem.image[0]).transform(TransPic()).into(holder.image)
        }

        holder.txtTitle.text = listItem.restaurant_name
        holder.averageCheck.text = listItem.average_check_restaurant.toString()
        holder.txtLocation.text = listItem.location

        holder.bind(listItem)
    }
}
