package com.example.themoviedb.domain

import com.example.themoviedb.domain.entities.remote.*
import com.example.themoviedb.domain.entities.remote.people.PeoplePopularResponse

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
    suspend fun getPopulars(page:String): PopularMovieResponse
    suspend fun getPopularTv(): TelevisionResponse
    suspend fun getJobs(): List<JobsResponse>
    suspend fun getTranslation(): List<String>
    suspend fun getLanguages(): List<Languajes>
    suspend fun getPersonsPopular(): PeoplePopularResponse
}