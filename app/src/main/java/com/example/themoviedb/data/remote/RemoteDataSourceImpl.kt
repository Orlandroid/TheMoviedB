package com.example.themoviedb.data.remote


import com.example.themoviedb.domain.RemoteDataSource
import com.example.themoviedb.domain.entities.remote.JobsResponse
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.remote.PopularResponse
import com.example.themoviedb.domain.entities.remote.TelevisionResponse
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

    override suspend fun getJobs(): List<JobsResponse> {
        return theMovieDbApi.getJobs()
    }

    override suspend fun getTranslation(): List<String> {
        return theMovieDbApi.getTranslation()
    }


}