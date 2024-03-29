package com.example.themoviedb.presentacion.ui.home.results

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.entities.local.CategoriesHome
import com.example.domain.entities.remote.ResultMovie
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentResultsBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.home.home.HomeMoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ResultsFragment(private val categories: CategoriesHome) :
    BaseFragment<FragmentResultsBinding>(R.layout.fragment_results) {

    private val viewModel: ResultsViewModel by viewModels()
    private var resultsAdapter = ResultsAdapter { clickOnResult(it) }

    override fun configureToolbar() =
        MainActivity.ToolbarConfiguration(
            showToolbar = true,
            clickOnBack = { requireActivity().finish() },
            clickOnSettings = {
                findNavController().navigate(HomeMoviesFragmentDirections.actionHomeMoviesFragmentToSettingsFragment())
            }
        )

    override fun setUpUi() = with(binding) {
        viewModel.categoriesHome = categories
        recycler.adapter = resultsAdapter
        getCurrentCategory()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }


    private fun clickOnResult(result: ResultMovie) {
        findNavController().navigate(
            HomeMoviesFragmentDirections.actionHomeMoviesFragmentToPeopleFragment(
                result.id
            )
        )
    }


    private fun getCurrentCategory() {
        getResults()
    }

    private fun getResults() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPopularsPagingSource.collectLatest { results ->
                    resultsAdapter.submitData(lifecycle, results)
                }
            }
        }
    }

}