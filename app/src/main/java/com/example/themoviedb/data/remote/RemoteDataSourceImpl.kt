package com.example.themoviedb.data.remote


import com.example.themoviedb.domain.RemoteDataSource
import com.example.themoviedb.domain.entities.remote.PopularMovieResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi,
) : RemoteDataSource {

    override suspend fun getProviders() = theMovieDbApi.getProviders()

    override suspend fun getPopulars(page:String):PopularMovieResponse = theMovieDbApi.getPopulars(page)

    override suspend fun getPopularTv() = theMovieDbApi.getPopularTv()

    override suspend fun getJobs() = theMovieDbApi.getJobs()

    override suspend fun getTranslation() = theMovieDbApi.getTranslation()

    override suspend fun getLanguages() = theMovieDbApi.getLanguages()

    override suspend fun getPersonsPopular() = theMovieDbApi.getPersonsPopular()
    override suspend fun nowPlaying(page: String): PopularMovieResponse  = theMovieDbApi.nowPlaying(page)
    override suspend fun upComing(page: String) = theMovieDbApi.upComing(page)


}