package com.example.themoviedb.domain.entities.remote

data class MoviesResponse(
    val page:Int,
    val results:List<Result>
)
