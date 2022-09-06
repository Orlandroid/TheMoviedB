package com.example.themoviedb.domain

import com.example.themoviedb.domain.entities.remote.JobsResponse
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.remote.PopularResponse
import com.example.themoviedb.domain.entities.remote.TelevisionResponse

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
    suspend fun getPopulars(): PopularResponse
    suspend fun getPopularTv(): TelevisionResponse
    suspend fun getJobs(): List<JobsResponse>
    suspend fun getTranslation(): List<String>
}