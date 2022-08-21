package com.example.themoviedb.domain

import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.domain.entities.TelevisionResponse

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
    suspend fun getPopulars(): PopularResponse
    suspend fun getPopularTv(): TelevisionResponse
}