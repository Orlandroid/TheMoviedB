package com.example.domain

import com.example.domain.entities.local.Department
import com.example.domain.entities.local.Translation

interface LocalDataSource {
    suspend fun insertDepartment(department: Department)
    suspend fun insertManyDepartment(departments: List<Department>)
    suspend fun getAllApartments(): List<Department>
    suspend fun getTranslation(): List<Translation>
}