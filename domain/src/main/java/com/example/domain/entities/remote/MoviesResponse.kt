package com.example.domain.entities.remote

data class MoviesResponse(
    val page:Int,
    val results:List<Result>
)
