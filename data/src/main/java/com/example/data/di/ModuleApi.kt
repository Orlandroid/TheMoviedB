package com.example.data.di


import com.example.data.Repository
import com.example.data.db.LocalDataSourceImpl
import com.example.data.helpers.ApiInterceptor
import com.example.data.remote.RemoteDataSourceImpl
import com.example.data.remote.TheMovieDbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApi {

    private const val BASE_URL_V3 = "https://api.themoviedb.org/3/"
    private const val ENABLE_RETROFIT_INTERCEPTOR_BODY = true

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            if (ENABLE_RETROFIT_INTERCEPTOR_BODY) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ApiInterceptor()).addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(true).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL_V3).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()


    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit): TheMovieDbApi =
        retrofit.create(TheMovieDbApi::class.java)


    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSourceImpl: RemoteDataSourceImpl, localDataSourceImpl: LocalDataSourceImpl
    ): Repository = Repository(remoteDataSourceImpl, localDataSourceImpl)

}