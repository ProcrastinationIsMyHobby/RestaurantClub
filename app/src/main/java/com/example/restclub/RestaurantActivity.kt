package com.example.restclub

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.restclub.adapter.ViewPagerAdapter
import com.example.restclub.common.Common
import com.example.restclub.interf.RetrofitServices
import com.example.restclub.model.Restaurant
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_restaurant.*
import kotlinx.android.synthetic.main.fragment_rest_info.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class RestaurantActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var pager2: ViewPager2
    lateinit var adapter: ViewPagerAdapter
    lateinit var mService: RetrofitServices
    lateinit var dialog: AlertDialog
    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        mService = Common.retrofitService

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        val arguments = intent.extras
        id = arguments!!["REST_ID"] as Int


        tabLayout = tab_layout
        pager2 = view_pager2

        val fm: FragmentManager = supportFragmentManager
        adapter = ViewPagerAdapter(fm, lifecycle)
        pager2.adapter = adapter

        initPages()
        getRest()

    }

    private fun initPages() {
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        pager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }


    private fun getRest() {

        dialog.show()
        mService.getInfoRestaurantAPI(id).enqueue(object : Callback<Restaurant> {

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
            }

            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {

                val rest =  response.body() as Restaurant

               pager2.restName.text = rest.restaurant_name


                dialog.dismiss()
            }
        })
    }
}