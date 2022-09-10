package com.example.themoviedb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.themoviedb.domain.entities.local.Translation

@Dao
interface ConfigurationDao {
    @Insert
    suspend fun insertManyTranslations(translations: List<Translation>)

    @Query("SELECT * FROM Translation")
    suspend fun getAllTranslations(): List<Translation>
}