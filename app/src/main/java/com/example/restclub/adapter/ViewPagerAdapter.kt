package com.example.restclub.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restclub.fragments.RestInfoFragment
import com.example.restclub.fragments.RestMenuFragment
import com.example.restclub.fragments.RestReviewsFragment


class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return RestMenuFragment()
            2 -> return RestReviewsFragment()
        }

        return RestInfoFragment()
    }


}