package com.example.themoviedb.presentacion.ui.moviedetail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentMovieDetailBinding
import com.example.themoviedb.domain.entities.remote.movies.Genre
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.chipscomponente.ChipsAdapter
import com.example.themoviedb.presentacion.ui.extensions.changeTitleToolbar
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val adapter = ChipsAdapter()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun setUpUi() = with(binding) {
        changeTitleToolbar(getString(R.string.peoples))
        recyclerCategories.adapter = adapter
        viewModel.getMovieId(args.movieId)
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.movieDetailResponse) {
            adapter.setData(getGenderForResponse(it.genres))
        }
    }

    private fun getGenderForResponse(genders: List<Genre>): List<ChipsAdapter.ChipElement> {
        val genderList = arrayListOf<ChipsAdapter.ChipElement>()
        genders.forEach {
            genderList.add(ChipsAdapter.ChipElement(message = it.name))
        }
        return genderList
    }

}