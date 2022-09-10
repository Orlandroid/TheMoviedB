package com.example.themoviedb.domain.entities.remote

import com.example.themoviedb.domain.entities.remote.Result

data class TelevisionResponse(
    val page: Int,
    val results: List<Result>
)
