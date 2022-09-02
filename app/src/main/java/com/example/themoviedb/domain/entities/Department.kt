package com.example.themoviedb.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviedb.domain.convertes.StringList

@Entity
data class Department(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val department: String,
    @TypeConverters(StringList::class)
    val jobs: List<String>,
)