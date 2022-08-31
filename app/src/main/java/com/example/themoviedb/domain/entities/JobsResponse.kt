package com.example.themoviedb.domain.entities

data class JobsResponse(
    val department: String,
    val jobs: List<String>
)