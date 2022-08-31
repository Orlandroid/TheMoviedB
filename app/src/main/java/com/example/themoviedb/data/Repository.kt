package com.example.themoviedb.data

import com.example.themoviedb.data.remote.RemoteDataSourceImpl
import com.example.themoviedb.domain.entities.JobsResponse
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.domain.entities.TelevisionResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
) {
    suspend fun getProviders(): MoviesProviders = remoteDataSource.getProviders()

    suspend fun getPopulars(): PopularResponse = remoteDataSource.getPopulars()


    suspend fun getPopularTv(): TelevisionResponse = remoteDataSource.getPopularTv()

    suspend fun getJobs(): List<JobsResponse> = remoteDataSource.getJobs()

}