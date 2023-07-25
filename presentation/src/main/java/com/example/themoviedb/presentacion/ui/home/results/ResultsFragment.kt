package com.example.themoviedb.presentacion.ui.home.results

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentResultsBinding
import com.example.domain.entities.remote.PopularMovieResponse
import com.example.domain.entities.remote.Result
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.myOnScrolled
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import com.example.themoviedb.presentacion.ui.home.adpters.ResultsAdapter
import com.example.themoviedb.presentacion.ui.home.home.HomeMoviesFragmentDirections
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
    private var popularAdapter: ResultsAdapter? = null

    override fun setUpUi() = with(binding) {
        popularAdapter = ResultsAdapter {
            val action =
                HomeMoviesFragmentDirections.actionHomeMoviesFragmentToPeopleFragment(it.id)
            findNavController().navigate(action)
        }
        recycler.adapter = popularAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) return@myOnScrolled
            if (totalPages > currentPage) {
                currentPage++
                canCallToTheNextPage = false
                getCurrentCategory()
            }
        }
        getCurrentCategory()
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
                viewModel.upComing(page = currentPage.toString())
            }

            HomeMoviesViewPagerAdapter.CategoriesHome.TOP_RATED -> {
                viewModel.topRated(page = currentPage.toString())
            }
        }
    }


    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.popularResponse, hasProgressTheView = true) {
            setData(it)
        }
        observeApiResult(viewModel.nowPlayingResponse, hasProgressTheView = true) {
            setData(it)
        }
        observeApiResult(viewModel.upComingResponse, hasProgressTheView = true) {
            setData(it)
        }
        observeApiResult(viewModel.topRatedResponse, hasProgressTheView = true) {
            setData(it)
        }
    }

    private fun setData(data: PopularMovieResponse) {
        totalPages = data.total_pages
        resultsList.addAll(data.results)
        popularAdapter?.setData(resultsList)
        canCallToTheNextPage = true
    }

}