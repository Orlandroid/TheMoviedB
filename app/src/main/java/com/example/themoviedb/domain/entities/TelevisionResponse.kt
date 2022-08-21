package com.example.themoviedb.domain.entities

data class TelevisionResponse(
    val page: Int,
    val results: List<Result>
)
