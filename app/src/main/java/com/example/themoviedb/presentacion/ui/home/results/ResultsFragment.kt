package com.example.themoviedb.presentacion.ui.home.results

import androidx.fragment.app.viewModels
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentResultsBinding
import com.example.themoviedb.domain.entities.remote.Result
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.myOnScrolled
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import com.example.themoviedb.presentacion.ui.home.adpters.ResultsAdapter
import com.example.themoviedb.presentacion.ui.home.home.HomeMoviesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultsFragment(private val categories: HomeMoviesViewPagerAdapter.CategoriesHome) :
    BaseFragment<FragmentResultsBinding>(R.layout.fragment_results) {

    private val viewModel: ResultsViewModel by viewModels()
    private var currentPage = 1
    private var totalPages = 0
    private var canCallToTheNextPage = true
    private var resultsList: ArrayList<Result> = arrayListOf()

    private val popularAdapter by lazy {
        ResultsAdapter()
    }

    override fun setUpUi() = with(binding) {
        getCurrentCategory()
        recycler.adapter = popularAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) return@myOnScrolled
            if (totalPages > currentPage) {
                currentPage++
                canCallToTheNextPage = false
                getCurrentCategory()
            }
        }

    }

    private fun getCurrentCategory() {
        when (categories) {
            HomeMoviesViewPagerAdapter.CategoriesHome.POPULAR -> {
                viewModel.getPopulars(page = currentPage.toString())
            }
            HomeMoviesViewPagerAdapter.CategoriesHome.NOW_PLAYING -> {
                viewModel.nowPlayingMovie(page = currentPage.toString())
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
            totalPages = it.total_pages
            resultsList.addAll(it.results)
            popularAdapter.setData(resultsList)
            canCallToTheNextPage = true
        }
        observeApiResult(viewModel.nowPlayingResponse, hasProgressTheView = true) {
            totalPages = it.total_pages
            resultsList.addAll(it.results)
            popularAdapter.setData(resultsList)
            canCallToTheNextPage = true
        }
    }
}