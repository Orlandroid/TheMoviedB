package com.example.domain.entities.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.domain.convertes.StringList

@Entity
data class Department(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val department: String,
    @TypeConverters(StringList::class)
    val jobs: List<String>,
)