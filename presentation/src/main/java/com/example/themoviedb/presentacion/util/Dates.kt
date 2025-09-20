package com.example.themoviedb.presentacion.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("SimpleDateFormat")
fun getDateFormant(dateStringFromBackend: String?, format: String): Date {
    dateStringFromBackend?.let {
        val sdf = SimpleDateFormat(format)
        return sdf.parse(it)
    }
    return Date()
}

fun getDateAsString(
    dateStringFromBackend: String?,
    formatCommonBackend: String = "yyyy-MM-dd'T'HH:mm:ss",
    formatWish: String,
    textErrorTransformDate: String = "N/A"
): String {
    dateStringFromBackend?.let {
        return SimpleDateFormat(formatWish, Locale.ENGLISH).format(
            getDateFormant(
                dateStringFromBackend,
                formatCommonBackend
            )
        )
    }
    return textErrorTransformDate
}