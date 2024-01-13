package com.example.themoviedb.presentacion.ui.moviedetail

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.domain.entities.remote.movies.Genre
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentMovieDetailBinding
import com.example.themoviedb.presentacion.base.BaseFragment
import com.example.themoviedb.presentacion.ui.MainActivity
import com.example.themoviedb.presentacion.ui.chipscomponente.ChipsAdapter
import com.example.themoviedb.presentacion.ui.extensions.changeTitleToolbar
import com.example.themoviedb.presentacion.ui.extensions.observeApiResult
import com.example.themoviedb.presentacion.util.ImageUtil
import com.example.themoviedb.presentacion.util.getAverageInCents
import com.example.themoviedb.presentacion.util.getDateAsString
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val adapter = ChipsAdapter()
    private val args: MovieDetailFragmentArgs by navArgs()
    private val imageUtil = ImageUtil()
    private val dateFromBackend = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    private val wishFormat = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH)

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(showToolbar = true)

    override fun setUpUi() = with(binding) {
        changeTitleToolbar(getString(R.string.movie_detail))
        recyclerCategories.adapter = adapter
        viewModel.getMovieId(args.movieId)
    }

    @SuppressLint("SetTextI18n")
    override fun observerViewModel() {
        super.observerViewModel()
        observeApiResult(viewModel.movieDetailResponse,
            shouldCloseTheViewOnApiError = true,
            hasProgressTheView = false,
            onLoading = { binding.skeleton.showSkeleton() },
            onFinishLoading = { binding.skeleton.showOriginal() }) {
            it.backdrop_path?.let { backdropPath ->
                Glide.with(requireContext())
                    .load(imageUtil.getBaseUrlImagePoster(urlImage = backdropPath))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.loading_animation).into(binding.imageBack)
            }
            it.poster_path?.let { posterPath ->
                Glide.with(requireContext())
                    .load(imageUtil.getBaseUrlImagePoster(urlImage = posterPath))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .placeholder(R.drawable.loading_animation).into(binding.imagePoster)
            }
            with(binding) {
                progressWithText.progressBar.progress = getAverageInCents(it.vote_average)
                progressWithText.progressPorcent.text = "${getAverageInCents(it.vote_average)} %"
                //titleMovie.text = it.title
                tvDescripcion.text = it.overview
                val date = getDateAsString(
                    formatCommonBackend = dateFromBackend.toPattern(),
                    dateStringFromBackend = it.release_date,
                    formatWish = wishFormat.toPattern()
                )
                tvDate.text = date
                tvDuration.text = "${it.runtime} Min"
                tvDuration.text = "${it.vote_count} Votes"
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