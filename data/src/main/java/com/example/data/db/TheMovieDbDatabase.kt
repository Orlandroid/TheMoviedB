package com.example.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.convertes.StringList
import com.example.domain.entities.local.Department
import com.example.domain.entities.local.Translation


@Database(entities = [Department::class, Translation::class], version = 2, exportSchema = false)
@TypeConverters(StringList::class)
abstract class TheMovieDbDatabase : RoomDatabase() {

    abstract fun departmentDao(): DepartmentDao

    abstract fun configurationDao(): ConfigurationDao


}