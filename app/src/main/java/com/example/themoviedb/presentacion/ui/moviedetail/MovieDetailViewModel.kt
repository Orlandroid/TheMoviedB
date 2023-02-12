package com.example.themoviedb.presentacion.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repository
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.remote.movies.MovieDetailResponse
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseViewModel
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _movieDetailResponse = MutableLiveData<Result<MovieDetailResponse>>()
    val movieDetailResponse: LiveData<Result<MovieDetailResponse>>
        get() = _movieDetailResponse


    fun getMovieId(movieId: Int) {
        viewModelScope.launch {
            safeApiCall(_movieDetailResponse, coroutineDispatchers) {
                val response = repository.getDetailMovie(movieId)
                withContext(Dispatchers.Main) {
                    _movieDetailResponse.value = Result.Success(response)
                }
            }
        }
    }

}