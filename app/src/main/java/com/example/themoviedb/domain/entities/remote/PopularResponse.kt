package com.example.themoviedb.domain.entities.remote

data class PopularResponse(
    val page: Int,
    val results: List<Result>
)

