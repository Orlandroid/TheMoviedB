package com.example.themoviedb.data.di

import android.content.Context
import androidx.room.Room
import com.example.themoviedb.data.db.TheMovieDbDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object ModuleDb {

    private const val DATABASE_NAME = "TheMovieDb"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): TheMovieDbDatabase {
        return Room.databaseBuilder(
            context,
            TheMovieDbDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDepartmentDao(db: TheMovieDbDatabase) = db.departmentDao()


}