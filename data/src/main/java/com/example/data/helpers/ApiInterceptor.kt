package com.example.data.helpers

import com.example.data.TheMovieDbAuth
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
        val url = original.url.newBuilder().addQueryParameter(LANGUAGE, LANGUAGE_MX).build()
        original.header("Authorization")
        original =
            original.newBuilder().addHeader("Authorization", TheMovieDbAuth.BEAR_TOKEN).url(url)
                .build()
        return chain.proceed(original)
    }
}