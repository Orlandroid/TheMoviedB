package com.example.themoviedb.data.db

import androidx.room.*
import com.example.themoviedb.domain.entities.Department
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {

    @Insert
    suspend fun insertDepartment(department: Department)

    @Insert
    suspend fun insertManyDepartment(department: List<Department>)

    @Query("SELECT * FROM Department")
    fun getAllADepartments(): List<Department>
}