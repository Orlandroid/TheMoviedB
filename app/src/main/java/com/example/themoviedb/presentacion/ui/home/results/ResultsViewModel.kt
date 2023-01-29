package com.example.themoviedb.presentacion.ui.home.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repository
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.remote.PopularMovieResponse
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseViewModel
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val _popularResponse = MutableLiveData<Result<PopularMovieResponse>>()
    val popularResponse: LiveData<Result<PopularMovieResponse>>
        get() = _popularResponse


    private val _nowPlayingResponse = MutableLiveData<Result<PopularMovieResponse>>()
    val nowPlayingResponse: LiveData<Result<PopularMovieResponse>>
        get() = _nowPlayingResponse


    fun getPopulars(page: String) {
        viewModelScope.launch {
            safeApiCall(_popularResponse, coroutineDispatchers) {
                val response = repository.getPopulars(page)
                withContext(Dispatchers.Main) {
                    _popularResponse.value = Result.Success(response)
                }
            }
        }
    }

    fun nowPlayingMovie(page: String) {
        viewModelScope.launch {
            safeApiCall(_nowPlayingResponse, coroutineDispatchers) {
                val response = repository.nowPlaying(page)
                withContext(Dispatchers.Main) {
                    _nowPlayingResponse.value = Result.Success(response)
                }
            }
        }
    }


}