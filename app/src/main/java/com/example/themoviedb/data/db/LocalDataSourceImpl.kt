package com.example.themoviedb.data.db

import com.example.themoviedb.domain.LocalDataSource
import com.example.themoviedb.domain.entities.local.Department
import com.example.themoviedb.domain.entities.local.Translation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val departmentDao: DepartmentDao,
    private val configurationDao: ConfigurationDao
) : LocalDataSource {
    override suspend fun insertDepartment(department: Department) = departmentDao.insertDepartment(department)

    override suspend fun insertManyDepartment(departments: List<Department>) = departmentDao.insertManyDepartment(departments)

    override suspend fun getAllApartments(): List<Department> = departmentDao.getAllADepartments()

    override suspend fun getTranslation(): List<Translation> = configurationDao.getAllTranslations()

}
