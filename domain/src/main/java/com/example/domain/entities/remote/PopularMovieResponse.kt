package com.example.domain.entities.remote

data class PopularMovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)