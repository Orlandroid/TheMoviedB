package com.example.data

import com.example.data.db.LocalDataSourceImpl
import com.example.data.remote.RemoteDataSourceImpl
import com.example.domain.entities.local.Department
import com.example.domain.entities.remote.JobsResponse
import com.example.domain.entities.remote.MoviesProviders
import com.example.domain.entities.remote.PopularMovieResponse
import com.example.domain.entities.remote.TelevisionResponse
import com.example.domain.entities.remote.people.PeoplePopularResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
) {

    suspend fun getPopulars(page: String): PopularMovieResponse = remoteDataSource.getPopulars(page)

    suspend fun getDetailMovie(movieId: Int) = remoteDataSource.getDetailMovie(movieId)

    suspend fun nowPlaying(page: String): PopularMovieResponse = remoteDataSource.nowPlaying(page)

    suspend fun upComing(page: String) = remoteDataSource.upComing(page)

    suspend fun topRated(page: String) = remoteDataSource.topRated(page)


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

    suspend fun getTranslation(): List<String> {
        val allTranslation = localDataSource.getTranslation()
        if (allTranslation.isNotEmpty()) {
            val translationFromCache = arrayListOf<String>()
            allTranslation.forEach {
                translationFromCache.add(
                    it.translation
                )
            }
            return translationFromCache
        }
        return remoteDataSource.getTranslation()
    }

    suspend fun getLanguages() = remoteDataSource.getLanguages()

    suspend fun getPersonsPopular(): PeoplePopularResponse = remoteDataSource.getPersonsPopular()

    suspend fun getProviders(): MoviesProviders = remoteDataSource.getProviders()

    suspend fun getPopularTv(): TelevisionResponse = remoteDataSource.getPopularTv()

}