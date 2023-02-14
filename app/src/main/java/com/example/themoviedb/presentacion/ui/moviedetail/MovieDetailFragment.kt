package com.example.themoviedb.presentacion.ui.moviedetail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentMovieDetailBinding
import com.example.themoviedb.domain.entities.remote.movies.Genre
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.chipscomponente.ChipsAdapter
import com.example.themoviedb.presentacion.ui.extensions.changeTitleToolbar
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import com.example.themoviedb.presentacion.util.ImageUtil
import com.example.themoviedb.presentacion.util.getAverageInCents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val adapter = ChipsAdapter()
    private val args: MovieDetailFragmentArgs by navArgs()
    private val imageUtil = ImageUtil()

    override fun setUpUi() = with(binding) {
        changeTitleToolbar(getString(R.string.movie_detail))
        recyclerCategories.adapter = adapter
        viewModel.getMovieId(args.movieId)
    }

    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(
            viewModel.movieDetailResponse,
            hasProgressTheView = false,
            onLoading = { binding.skeleton.showSkeleton() },
            onFinishLoading = { binding.skeleton.showOriginal() }) {
            it.backdrop_path?.let { backdropPath ->
                Glide.with(requireContext())
                    .load(imageUtil.getBaseUrlImagePoster(urlImage = backdropPath))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.loading_animation).into(binding.imageMovie)
            }
            with(binding) {
                progressWithText.progressBar.progress = getAverageInCents(it.vote_average)
                progressWithText.progressPorcent.text = "${getAverageInCents(it.vote_average)} %"
                titleMovie.text = it.title
                tvDescripcion.text = it.overview
            }
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