package com.example.domain.convertes

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class StringList {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<String?>?): String? {
        return gson.toJson(someObjects)
    }
}
