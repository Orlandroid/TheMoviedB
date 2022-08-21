package com.example.themoviedb.data.remote


import com.example.themoviedb.domain.RemoteDataSource
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.domain.entities.TelevisionResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi,
) : RemoteDataSource {

    override suspend fun getProviders(): MoviesProviders {
        return theMovieDbApi.getProviders()
    }

    override suspend fun getPopulars(): PopularResponse {
        return theMovieDbApi.getPopulars()
    }

    override suspend fun getPopularTv(): TelevisionResponse {
        return theMovieDbApi.getPopularTv()
    }


}