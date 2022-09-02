package com.example.themoviedb.data.db

import com.example.themoviedb.domain.LocalDataSource
import com.example.themoviedb.domain.entities.Department
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val dao: DepartmentDao
) : LocalDataSource {
    override suspend fun insertDepartment(department: Department) = dao.insertDepartment(department)

    override suspend fun insertManyDepartment(departments: List<Department>) =
        dao.insertManyDepartment(departments)

    override suspend fun getAllApartments(): List<Department> = dao.getAllADepartments()

}
