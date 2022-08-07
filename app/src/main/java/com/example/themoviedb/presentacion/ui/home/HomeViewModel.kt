package com.example.themoviedb.presentacion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Repositorio
import com.example.themoviedb.data.di.CoroutineDispatchers
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import com.example.themoviedb.domain.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositorio: Repositorio,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val errorNetwork = "Error verifica tu conexion"

    private val _providers = MutableLiveData<Result<PopularResponse>>()
    val providers: LiveData<Result<PopularResponse>>
        get() = _providers


    fun getProviders() {
        viewModelScope.launch(coroutineDispatchers.io) {
            withContext(coroutineDispatchers.main) {
                _providers.value = Result.Loading
            }
            if (!networkHelper.isNetworkConnected()) {
                withContext(coroutineDispatchers.main) {
                    _providers.value = Result.ErrorNetwork(errorNetwork)
                }
                return@launch
            }
            try {
                val response = repositorio.getPopulars()
                withContext(coroutineDispatchers.main) {
                    _providers.value = Result.Success(response)
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    _providers.value = Result.Error(e.message ?: "Error app")
                }
            }
        }
    }
}