package com.example.themoviedb.presentacion.ui.home.now_playing

import androidx.fragment.app.viewModels
import com.example.domain.entities.remote.Result
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentResultsBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.extensions.myOnScrolled
import com.example.themoviedb.presentacion.ui.home.adpters.ResultsAdapter
import com.example.themoviedb.presentacion.ui.home.results.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : BaseFragment<FragmentResultsBinding>(R.layout.fragment_results) {


    private val viewModel: ResultsViewModel by viewModels()
    private var currentPage = 1
    private var totalPages = 0
    private var canCallToTheNextPage = true
    private var resultsList: ArrayList<Result> = arrayListOf()

    private val popularAdapter by lazy {
        ResultsAdapter()
    }

    override fun setUpUi() = with(binding) {
        viewModel.nowPlayingMovie(page = currentPage.toString())
        recycler.adapter = popularAdapter
        recycler.myOnScrolled {
            if (!canCallToTheNextPage) return@myOnScrolled
            if (totalPages > currentPage) {
                currentPage++
                canCallToTheNextPage = false
                viewModel.nowPlayingMovie(page = currentPage.toString())
            }
        }
    }

    override fun observerViewModel() {
        super.observerViewModel()
        /*
        observeApiResult(viewModel.nowPlayingResponse, hasProgressTheView = true) {
            totalPages = it.total_pages
            resultsList.addAll(it.results)
            popularAdapter.setData(resultsList)
            canCallToTheNextPage = true
        }*/
    }


}