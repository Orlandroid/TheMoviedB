package com.example.themoviedb.domain.entities.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Translation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val translations: String,
)