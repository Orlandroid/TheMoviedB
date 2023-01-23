package com.example.themoviedb.presentacion.ui.home.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repository
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.remote.PopularResponse
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import com.example.themoviedb.domain.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _popularTvResponse = MutableLiveData<Result<PopularResponse>>()
    val popularTvResponse: LiveData<Result<PopularResponse>>
        get() = _popularTvResponse


    fun getPopularTv() {
        viewModelScope.launch(coroutineDispatchers.io) {
            withContext(coroutineDispatchers.main) {
                _popularTvResponse.value = Result.Loading
            }
            if (!networkHelper.isNetworkConnected()) {
                withContext(coroutineDispatchers.main) {
                    _popularTvResponse.value = Result.ErrorNetwork("")
                }
                return@launch
            }
            try {
                val response = repository.getPopulars()
                withContext(coroutineDispatchers.main) {
                    _popularTvResponse.value = Result.Success(response)
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    _popularTvResponse.value = Result.Error(e.message ?: "Error app")
                }
            }
        }
    }


}