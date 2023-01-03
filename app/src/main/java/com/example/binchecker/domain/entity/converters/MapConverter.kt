package com.example.binchecker.domain.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConverter {

    @TypeConverter
    fun toString(map: Map<String, String>?): String {
        if (map == null) {
            return ""
        }
        return Gson().toJson(map)
    }

    @TypeConverter
    fun toMap(string: String): Map<String, String>? {
        if (string == "") {
            return null
        }
        val mapType = object : TypeToken<Map<String, String>?>() {}.type
        return Gson().fromJson(string, mapType)
    }
}