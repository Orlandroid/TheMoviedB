package com.example.themoviedb.presentacion.ui.translations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.domain.entities.remote.Languajes
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.domain.state.Result
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TranslationsViewModel @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val errorNetwork = "Error verifica tu conexion"

    private val _translationResponse = MutableLiveData<Result<List<String>>>()
    val translationResponse: LiveData<Result<List<String>>>
        get() = _translationResponse

    private val _languagesResponse = MutableLiveData<Result<List<Languajes>>>()
    val languagesResponse: LiveData<Result<List<Languajes>>>
        get() = _languagesResponse

    fun getTranslations() {
        viewModelScope.launch(coroutineDispatchers.io) {
            doLoadingAndConnection(liveData = _translationResponse)
            try {
                val response = repository.getTranslation()
                if (response.isEmpty()) {
                    withContext(coroutineDispatchers.main) {
                        _translationResponse.value = Result.EmptyList
                    }
                }
                withContext(coroutineDispatchers.main) {
                    _translationResponse.value = Result.Success(response)
                }
            } catch (e: Exception) {
                withContext(coroutineDispatchers.main) {
                    _translationResponse.value = Result.Error(e.message ?: "Error app")
                }
            }
        }
    }


    private fun <T> doLoadingAndConnection(
        dispatcher: CoroutineDispatcher = coroutineDispatchers.io,
        liveData: MutableLiveData<Result<T>>
    ) {
        viewModelScope.launch(dispatcher) {
            withContext(Dispatchers.Main) {
                liveData.value = Result.Loading
            }
            if (!networkHelper.isNetworkConnected()) {
                withContext(coroutineDispatchers.main) {
                    liveData.value = Result.ErrorNetwork(errorNetwork)
                }
                return@launch
            }
        }
    }


}