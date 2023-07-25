package com.example.data.db

import com.example.domain.LocalDataSource
import com.example.domain.entities.local.Department
import com.example.domain.entities.local.Translation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val departmentDao: DepartmentDao,
    private val configurationDao: ConfigurationDao
) : LocalDataSource {
    override suspend fun insertDepartment(department: Department) =
        departmentDao.insertDepartment(department)

    override suspend fun insertManyDepartment(departments: List<Department>) =
        departmentDao.insertManyDepartment(departments)

    override suspend fun getAllApartments(): List<Department> = departmentDao.getAllADepartments()

    override suspend fun getTranslation(): List<Translation> = configurationDao.getAllTranslations()

}
