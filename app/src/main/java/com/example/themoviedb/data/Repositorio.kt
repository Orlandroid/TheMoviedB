package com.example.themoviedb.data

import com.example.themoviedb.data.remote.RemoteDataSourceImpl
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.domain.entities.TelevisionResponse
import com.example.themoviedb.domain.state.Result
import okhttp3.ResponseBody
import javax.inject.Inject

class Repositorio @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
) {
    suspend fun getProviders(): MoviesProviders {
        return remoteDataSource.getProviders()
    }

    suspend fun getPopulars(): PopularResponse {
        return remoteDataSource.getPopulars()
    }

    suspend fun getPopularTv(): TelevisionResponse {
        return remoteDataSource.getPopularTv()
    }

}