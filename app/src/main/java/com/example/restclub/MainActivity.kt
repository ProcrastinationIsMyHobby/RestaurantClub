package com.example.restclub

import android.app.AlertDialog
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var mService: RetrofitServices
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyAdapter
    lateinit var dialog: AlertDialog
    private lateinit var search: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search = search_bar



        mService = Common.retrofitService
        recycler.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                dialog.show()
                mService.searchAPI(query!!).enqueue(object : Callback<Root> {

                    override fun onFailure(call: Call<Root>, t: Throwable) {

                        Toast.makeText(this@MainActivity,"Не удалось подключиться(((", Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(call: Call<Root>?, response: Response<Root>?) {
                        if ((response != null) && (response.body() != null )) {
                            adapter = MyAdapter(baseContext, response.body() as Root)
                            adapter.notifyDataSetChanged()
                            recycler.adapter = adapter
                        }
                        else
                        {
                            Toast.makeText(this@MainActivity,"Не найдено", Toast.LENGTH_LONG).show()

                        }


                        dialog.dismiss()
                    }
                })


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

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