package com.example.themoviedb.data.helpers

import com.example.themoviedb.data.TheMovieDbAuth
import okhttp3.Interceptor
import okhttp3.Response


class ApiInterceptor : Interceptor {
    companion object {
        private const val API_KEY = "api_key"
        private const val LANGUAGE = "language"
        private const val LANGUAGE_MX = "es-MX"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url =
            original.url.newBuilder().addQueryParameter(API_KEY, TheMovieDbAuth.API_KEY)
                .addQueryParameter(LANGUAGE, LANGUAGE_MX).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}