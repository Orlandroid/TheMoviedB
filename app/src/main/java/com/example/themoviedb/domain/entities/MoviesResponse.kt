package com.example.themoviedb.domain.entities

data class MoviesResponse(
    val page:Int,
    val results:List<Result>
)
