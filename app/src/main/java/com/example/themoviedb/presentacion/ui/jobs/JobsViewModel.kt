package com.example.themoviedb.presentacion.ui.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repository
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.JobsResponse
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import com.example.themoviedb.domain.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val repositorio: Repository,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val errorNetwork = "Error verifica tu conexion"

    private val _jobsResponse = MutableLiveData<Result<List<JobsResponse>>>()
    val jobsResponse: LiveData<Result<List<JobsResponse>>>
        get() = _jobsResponse

    fun getJobs() {
        viewModelScope.launch(coroutineDispatchers.io) {
            withContext(coroutineDispatchers.main) {
                _jobsResponse.value = Result.Loading
            }
            if (!networkHelper.isNetworkConnected()) {
                withContext(coroutineDispatchers.main) {
                    _jobsResponse.value = Result.ErrorNetwork(errorNetwork)
                }
                return@launch
            }
            try {
                val response = repositorio.getDepartments()
                if (response.isEmpty()) {
                    withContext(coroutineDispatchers.main) {
                        _jobsResponse.value = Result.EmptyList
                    }
                }
                withContext(coroutineDispatchers.main) {
                    _jobsResponse.value = Result.Success(response)
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    _jobsResponse.value = Result.Error(e.message ?: "Error app")
                }
            }
        }
    }

}