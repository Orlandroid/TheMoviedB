package com.example.themoviedb.presentacion.ui.people

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repository
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.remote.JobsResponse
import com.example.themoviedb.domain.entities.remote.people.PeoplePopularResponse
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val errorNetwork = "Error verifica tu conexion"

    private val _peoplePopularResponse = MutableLiveData<Result<PeoplePopularResponse>>()
    val peoplePopularResponse: LiveData<Result<PeoplePopularResponse>>
        get() = _peoplePopularResponse


    fun getPeoplePopular() {
        viewModelScope.launch(coroutineDispatchers.io) {
            withContext(coroutineDispatchers.main) {
                _peoplePopularResponse.value = Result.Loading
            }
            if (!networkHelper.isNetworkConnected()) {
                withContext(coroutineDispatchers.main) {
                    _peoplePopularResponse.value = Result.ErrorNetwork(errorNetwork)
                }
                return@launch
            }
            try {
                val response = repository.getPersonsPopular()
                if (response.results.isEmpty()) {
                    withContext(coroutineDispatchers.main) {
                        _peoplePopularResponse.value = Result.EmptyList
                    }
                }
                withContext(coroutineDispatchers.main) {
                    _peoplePopularResponse.value = Result.Success(response)
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    _peoplePopularResponse.value = Result.Error(e.message ?: "Error app")
                }
            }
        }
    }

    fun getPeople() {
        viewModelScope.launch {
            safeApiCall(_peoplePopularResponse, coroutineDispatchers) {
                val response = repository.getPersonsPopular()
                withContext(Dispatchers.Main) {
                    _peoplePopularResponse.value = Result.Success(response)
                }
            }
        }
    }

}