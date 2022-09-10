package com.example.themoviedb.domain

import com.example.themoviedb.domain.entities.remote.*

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
    suspend fun getPopulars(): PopularResponse
    suspend fun getPopularTv(): TelevisionResponse
    suspend fun getJobs(): List<JobsResponse>
    suspend fun getTranslation(): List<String>
    suspend fun getLanguages(): List<Languajes>
}