package com.example.themoviedb.data.remote

import com.example.themoviedb.domain.entities.JobsResponse
import com.example.themoviedb.domain.entities.MoviesProviders
import com.example.themoviedb.domain.entities.PopularResponse
import com.example.themoviedb.domain.entities.TelevisionResponse
import retrofit2.http.GET
import com.example.themoviedb.domain.state.Result

interface TheMovieDbApi {

    @GET("watch/providers/movie")
    suspend fun getProviders(): MoviesProviders

    @GET("movie/popular")
    suspend fun getPopulars(): PopularResponse

    @GET("tv/popular")
    suspend fun getPopularTv(): TelevisionResponse

    @GET("movie/upcoming")
    suspend fun getUpComming(): Result<String>

    @GET("movie/top_rated")
    suspend fun getTopRated(): Result<String>

    @GET("movie/latest")
    suspend fun getLasted(): Result<String>

    @GET("/movie/{movie_id}")
    suspend fun getDetailMovie(): Result<String>

    @GET("/genre/movie/list")
    suspend fun getGeneres(): Result<String>

    @GET("list/{id}")
    suspend fun getAllMoviesByUserId(): Result<String>

    @GET("configuration/jobs")
    suspend fun getJobs(): List<JobsResponse>

}