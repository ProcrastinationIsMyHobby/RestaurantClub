package com.example.restclub

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restclub.adapter.MyAdapter
import com.example.restclub.common.Common
import com.example.restclub.interf.RetrofitServices
import com.example.restclub.model.Root
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        recycler.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllRestList()

    }

    private fun getAllRestList() {

        dialog.show()
        mService.getStartRestaurants().enqueue(object : Callback<Root> {

            override fun onFailure(call: Call<Root>, t: Throwable) {

            }

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                adapter = MyAdapter(baseContext, response.body() as Root)
                adapter.notifyDataSetChanged()
                recycler.adapter = adapter

                dialog.dismiss()
            }
        })
    }
}