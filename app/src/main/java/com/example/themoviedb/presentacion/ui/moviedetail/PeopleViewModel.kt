package com.example.themoviedb.presentacion.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repository
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.remote.people.PeoplePopularResponse
import com.example.themoviedb.domain.state.Result
import com.example.themoviedb.presentacion.base.BaseViewModel
import com.example.themoviedb.presentacion.helpers.NetworkHelper
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


    private val _peoplePopularResponse = MutableLiveData<Result<PeoplePopularResponse>>()
    val peoplePopularResponse: LiveData<Result<PeoplePopularResponse>>
        get() = _peoplePopularResponse


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