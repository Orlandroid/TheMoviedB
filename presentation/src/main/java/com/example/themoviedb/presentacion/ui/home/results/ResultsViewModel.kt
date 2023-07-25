package com.example.themoviedb.presentacion.ui.home.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.domain.entities.remote.PopularMovieResponse
import com.example.domain.state.Result
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

    private val _upComingResponse = MutableLiveData<Result<PopularMovieResponse>>()
    val upComingResponse: LiveData<Result<PopularMovieResponse>>
        get() = _upComingResponse

    private val _topRatedResponse = MutableLiveData<Result<PopularMovieResponse>>()
    val topRatedResponse: LiveData<Result<PopularMovieResponse>>
        get() = _topRatedResponse


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

    fun upComing(page: String) {
        viewModelScope.launch {
            safeApiCall(_upComingResponse, coroutineDispatchers) {
                val response = repository.upComing(page)
                withContext(Dispatchers.Main) {
                    _upComingResponse.value = Result.Success(response)
                }
            }
        }
    }

    fun topRated(page: String) {
        viewModelScope.launch {
            safeApiCall(_topRatedResponse, coroutineDispatchers) {
                val response = repository.topRated(page)
                withContext(Dispatchers.Main) {
                    _topRatedResponse.value = Result.Success(response)
                }
            }
        }
    }


}