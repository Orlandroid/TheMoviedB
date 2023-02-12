package com.example.themoviedb.data.remote

import com.example.themoviedb.domain.entities.remote.*
import com.example.themoviedb.domain.entities.remote.movies.MovieDetailResponse
import com.example.themoviedb.domain.entities.remote.people.PeoplePopularResponse
import retrofit2.http.GET
import com.example.themoviedb.domain.state.Result
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

    @GET("movie/upcoming")
    suspend fun getUpComming(): Result<String>

    @GET("movie/top_rated")
    suspend fun getTopRated(): Result<String>

    @GET("movie/latest")
    suspend fun getLasted(): Result<String>


    @GET("/genre/movie/list")
    suspend fun getGeneres(): Result<String>

    @GET("list/{id}")
    suspend fun getAllMoviesByUserId(): Result<String>
}