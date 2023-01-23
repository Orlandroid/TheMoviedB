package com.example.themoviedb.domain.entities.remote

data class TelevisionResponse(
    val page: Int,
    val results: List<Result>
)
