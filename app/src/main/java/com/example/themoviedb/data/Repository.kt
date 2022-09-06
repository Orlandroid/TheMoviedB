package com.example.themoviedb.data

import com.example.themoviedb.data.db.LocalDataSourceImpl
import com.example.themoviedb.data.remote.RemoteDataSourceImpl
import com.example.themoviedb.domain.entities.*
import com.example.themoviedb.domain.entities.local.Department
import com.example.themoviedb.domain.entities.remote.JobsResponse
import com.example.themoviedb.domain.entities.remote.PopularResponse
import com.example.themoviedb.domain.entities.remote.TelevisionResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
) {

    suspend fun getPopulars(): PopularResponse = remoteDataSource.getPopulars()


    suspend fun getDepartments(): List<JobsResponse> {
        val allIDeparments = localDataSource.getAllApartments()
        if (allIDeparments.isNotEmpty()) {
            val departmentsFromLocalCache = arrayListOf<JobsResponse>()
            allIDeparments.forEach {
                departmentsFromLocalCache.add(
                    JobsResponse(
                        department = it.department,
                        jobs = it.jobs
                    )
                )
            }
            return departmentsFromLocalCache
        }
        val departments = arrayListOf<Department>()
        val response = remoteDataSource.getJobs()
        response.forEach {
            val currentDepartment = Department(
                department = it.department,
                jobs = it.jobs
            )
            departments.add(currentDepartment)
        }
        localDataSource.insertManyDepartment(departments)
        return response
    }

    suspend fun getTranslation() = remoteDataSource.getTranslation()

    suspend fun getProviders(): MoviesProviders = remoteDataSource.getProviders()

    suspend fun getPopularTv(): TelevisionResponse = remoteDataSource.getPopularTv()

}