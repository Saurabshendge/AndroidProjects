package com.trioangle.systemtask.utils

import com.google.gson.Gson

object JSONUtils {
    fun toJson(element: Any): String {
        val gson = Gson()
        return gson.toJson(element)
    }

    fun <T> fromJson(json: String?, classOfT: Class<T>?): T {
        val gson = Gson()
        return gson.fromJson(json, classOfT)
    }
}