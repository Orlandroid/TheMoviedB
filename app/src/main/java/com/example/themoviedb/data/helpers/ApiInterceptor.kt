package com.example.themoviedb.data.helpers

import com.example.themoviedb.data.TheMovieDbAuth
import okhttp3.Interceptor
import okhttp3.Response


class ApiInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url =
            original.url.newBuilder().addQueryParameter("apikey", TheMovieDbAuth.API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}