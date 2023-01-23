package com.example.themoviedb.presentacion.ui.home.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themoviedb.presentacion.ui.home.now_playing.NowPlayingFragment
import com.example.themoviedb.presentacion.ui.home.popular.PopularFragment
import com.example.themoviedb.presentacion.ui.home.top_rated.TopRatedFragment
import com.example.themoviedb.presentacion.ui.home.upcoming.UpComingFragment

class HomeMoviesViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PopularFragment()
            }
            1 -> {
                NowPlayingFragment()
            }
            2 -> {
                UpComingFragment()
            }
            else -> {
                TopRatedFragment()
            }
        }
    }

}