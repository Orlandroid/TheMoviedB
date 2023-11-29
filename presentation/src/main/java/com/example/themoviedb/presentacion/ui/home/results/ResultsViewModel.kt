package com.example.themoviedb.presentacion.ui.home.results

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.Repository
import com.example.data.di.CoroutineDispatchers
import com.example.data.pagination.PopularsPagingSource
import com.example.data.pagination.getPagingConfig
import com.example.data.remote.TheMovieDbApi
import com.example.domain.entities.local.CategoriesHome
import com.example.domain.entities.remote.ResultMovie
import com.example.themoviedb.presentacion.base.BaseViewModel
import com.example.themoviedb.presentacion.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: Repository,
    private val theMovieDbApi: TheMovieDbApi,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    lateinit var categoriesHome: CategoriesHome

    private lateinit var popularsPagingSource: PopularsPagingSource

    val getPopularsPagingSource: Flow<PagingData<ResultMovie>> =
        Pager(
            config = getPagingConfig(),
            pagingSourceFactory = {
                popularsPagingSource = PopularsPagingSource(service = theMovieDbApi, categoriesHome)
                popularsPagingSource
            }
        ).flow.cachedIn(viewModelScope)

    fun refreshCharactersPagingSource() = popularsPagingSource.invalidate()


}