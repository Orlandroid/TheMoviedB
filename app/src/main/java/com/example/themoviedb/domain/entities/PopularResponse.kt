package com.example.themoviedb.domain.entities

data class PopularResponse(
    val page: Int,
    val results: List<Result>
)

