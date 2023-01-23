package com.example.themoviedb.data.remote


import com.example.themoviedb.domain.RemoteDataSource
import com.example.themoviedb.domain.entities.remote.*
import com.example.themoviedb.domain.entities.remote.people.PeoplePopularResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi,
) : RemoteDataSource {

    override suspend fun getProviders(): MoviesProviders = theMovieDbApi.getProviders()

    override suspend fun getPopulars(): PopularResponse = theMovieDbApi.getPopulars()

    override suspend fun getPopularTv(): TelevisionResponse = theMovieDbApi.getPopularTv()

    override suspend fun getJobs(): List<JobsResponse> = theMovieDbApi.getJobs()

    override suspend fun getTranslation(): List<String> = theMovieDbApi.getTranslation()

    override suspend fun getLanguages(): List<Languajes> = theMovieDbApi.getLanguages()

    override suspend fun getPersonsPopular(): PeoplePopularResponse = theMovieDbApi.getPersonsPopular()


}