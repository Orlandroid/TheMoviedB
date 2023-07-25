package com.example.domain

import com.example.domain.entities.remote.JobsResponse
import com.example.domain.entities.remote.Languajes
import com.example.domain.entities.remote.MoviesProviders
import com.example.domain.entities.remote.PopularMovieResponse
import com.example.domain.entities.remote.TelevisionResponse
import com.example.domain.entities.remote.movies.MovieDetailResponse
import com.example.domain.entities.remote.people.PeoplePopularResponse

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
    suspend fun getPopulars(page: String): PopularMovieResponse
    suspend fun getPopularTv(): TelevisionResponse
    suspend fun getJobs(): List<JobsResponse>
    suspend fun getTranslation(): List<String>
    suspend fun getLanguages(): List<Languajes>
    suspend fun getPersonsPopular(): PeoplePopularResponse
    suspend fun nowPlaying(page: String): PopularMovieResponse
    suspend fun upComing(page: String): PopularMovieResponse
    suspend fun topRated(page: String): PopularMovieResponse
    suspend fun getDetailMovie(movieId: Int): MovieDetailResponse

}