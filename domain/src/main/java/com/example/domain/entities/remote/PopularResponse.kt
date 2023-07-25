package com.example.domain.entities.remote

data class PopularResponse(
    val page: Int,
    val results: List<Result>
)

