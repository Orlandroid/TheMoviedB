package com.example.themoviedb.domain

import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
    suspend fun getPopulars(): PopularResponse
}