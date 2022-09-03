package com.example.themoviedb.domain

import com.example.themoviedb.domain.entities.Department
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertDepartment(department: Department)
    suspend fun insertManyDepartment(departments: List<Department>)
    suspend fun getAllApartments(): List<Department>
}