package com.example.themoviedb.presentacion.ui.home.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.domain.entities.local.CategoriesHome
import com.example.themoviedb.presentacion.ui.home.results.ResultsFragment

class HomeMoviesViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ResultsFragment(CategoriesHome.POPULAR)
            }
            1 -> {
                ResultsFragment(CategoriesHome.NOW_PLAYING)
            }
            2 -> {
                ResultsFragment(CategoriesHome.UP_COMING)
            }
            else -> {
                ResultsFragment(CategoriesHome.TOP_RATED)
            }
        }
    }

}