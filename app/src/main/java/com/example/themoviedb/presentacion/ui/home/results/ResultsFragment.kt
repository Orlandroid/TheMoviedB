package com.example.themoviedb.presentacion.ui.home.results

import androidx.fragment.app.viewModels
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentResultsBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import com.example.themoviedb.presentacion.ui.home.adpters.ResultsAdapter
import com.example.themoviedb.presentacion.ui.home.home.HomeMoviesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultsFragment(private val categories: HomeMoviesViewPagerAdapter.CategoriesHome) :
    BaseFragment<FragmentResultsBinding>(R.layout.fragment_results) {

    private val viewModel: ResultsViewModel by viewModels()

    private val popularAdapter by lazy {
        ResultsAdapter()
    }

    override fun setUpUi() {
        getCurrentCategory()
        with(binding) {
            recycler.adapter = popularAdapter
        }
    }

    private fun getCurrentCategory() {
        when (categories) {
            HomeMoviesViewPagerAdapter.CategoriesHome.POPULAR -> {
                viewModel.getPopulars("1")
            }
            HomeMoviesViewPagerAdapter.CategoriesHome.NOW_PLAYING -> {

            }
            HomeMoviesViewPagerAdapter.CategoriesHome.UP_COMING -> {

            }
            HomeMoviesViewPagerAdapter.CategoriesHome.TOP_RATED -> {

            }
        }
    }


    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.popularResponse, hasProgressTheView = true) {
            popularAdapter.setData(it.results)
        }
    }
}