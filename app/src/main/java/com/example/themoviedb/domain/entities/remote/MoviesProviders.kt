package com.example.themoviedb.domain.entities

data class MoviesProviders(
    val results: List<Provider>
)

data class Provider(
    val display_priority: Int,
    val logo_path: String,
    val provider_name: String,
    val provider_id: Int
)