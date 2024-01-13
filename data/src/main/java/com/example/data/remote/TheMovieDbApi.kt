package com.example.data.remote

import com.example.domain.entities.remote.JobsResponse
import com.example.domain.entities.remote.Languajes
import com.example.domain.entities.remote.MoviesProviders
import com.example.domain.entities.remote.PopularMovieResponse
import com.example.domain.entities.remote.TelevisionResponse
import com.example.domain.entities.remote.movies.MovieDetailResponse
import com.example.domain.entities.remote.people.PeoplePopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {


    @GET("movie/popular")
    suspend fun getPopulars(@Query("page") page: String): PopularMovieResponse

    @GET("movie/now_playing")
    suspend fun nowPlaying(@Query("page") page: String): PopularMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Int): MovieDetailResponse

    @GET("movie/upcoming")
    suspend fun upComing(@Query("page") page: String): PopularMovieResponse

    @GET("movie/top_rated")
    suspend fun topRated(@Query("page") page: String): PopularMovieResponse

    @GET("watch/providers/movie")
    suspend fun getProviders(): MoviesProviders

    @GET("tv/popular")
    suspend fun getPopularTv(): TelevisionResponse

    @GET("configuration/jobs")
    suspend fun getJobs(): List<JobsResponse>

    @GET("configuration/primary_translations")
    suspend fun getTranslation(): List<String>

    @GET("configuration/languages")
    suspend fun getLanguages(): List<Languajes>

    @GET("person/popular")
    suspend fun getPersonsPopular(): PeoplePopularResponse
}