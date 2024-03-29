package com.example.themoviedb.presentacion.ui.home.home

import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentHomeMoviesBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.changeTitleToolbar
import com.example.themoviedb.presentacion.ui.extensions.showToolbar
import com.google.android.material.tabs.TabLayoutMediator


class HomeMoviesFragment : BaseFragment<FragmentHomeMoviesBinding>(R.layout.fragment_home_movies) {

    private lateinit var adapter: HomeMoviesViewPagerAdapter

    override fun setUpUi() {
        showToolbar()
        changeTitleToolbar("Movies")
        with(binding) {
            adapter = HomeMoviesViewPagerAdapter(this@HomeMoviesFragment)
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false
            setUpTabNames()

        }
    }

    private fun setUpTabNames() {
        TabLayoutMediator(binding.taLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.polular)
                1 -> tab.text = resources.getString(R.string.now_playing)
                2 -> tab.text = resources.getString(R.string.up_coming)
                3 -> tab.text = resources.getString(R.string.top_rated)
            }
        }.attach()
    }


}