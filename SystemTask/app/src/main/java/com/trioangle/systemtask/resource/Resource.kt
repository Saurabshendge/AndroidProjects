package com.trioangle.systemtask.resource

import org.json.JSONObject
import java.util.*

sealed class Resource<out T> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class FailureJSONObject(val data: JSONObject) : Resource<Nothing>()
    data class FailureMessage(val data: String) : Resource<Nothing>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    data class ConnectionTimeOut(val data: String) : Resource<Nothing>()
    data class NoInternet(val data: String) : Resource<Nothing>()
}
