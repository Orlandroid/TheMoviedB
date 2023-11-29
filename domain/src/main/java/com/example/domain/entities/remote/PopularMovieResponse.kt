package com.example.domain.entities.remote

data class PopularMovieResponse(
    val page: Int,
    val results: List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)