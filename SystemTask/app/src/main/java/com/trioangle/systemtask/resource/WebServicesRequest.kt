package com.trioangle.systemtask.resource

import android.util.Log


import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class WebServicesRequest {

    companion object{
        const val INPUT_VALUE_ERROR="1001"
        const val EXCEPTION="1002"
        const val COMMON_ERROR="1003"
        const val MAINTENANCE="1004"
        const val UN_AUTHENTICATED=401
    }

    suspend fun<T:Any> webApiCallRequest(call:suspend ()->Response<T>): Resource<T> {
        var resource: Resource<T>?=null
        try {
            val response=call.invoke()
            if (response.isSuccessful){
                return Resource.Success(response.body()!!)
            }else{
                val error= response.errorBody()?.string()
                if (response.code()== UN_AUTHENTICATED){
                    Log.e("TAG", "webApiCallRequest: UN AUTH")
                }else{
                    error?.let {
                        try {
                            JSONObject(it).has("error_code").apply {
                                if (this){
                                    val errorCode=JSONObject(it).getString("error_code")
                                    when(errorCode.toInt()){
                                        INPUT_VALUE_ERROR.toInt()->{
                                            val errorJson=JSONObject(it).getJSONObject("errors")
                                            resource= Resource.FailureJSONObject(errorJson)
                                        }
                                        EXCEPTION.toInt()->{
                                            resource=
                                                Resource.FailureMessage(JSONObject(it).getString("message"))
                                        }
                                        COMMON_ERROR.toInt()->{
                                            resource=
                                                Resource.FailureMessage(JSONObject(it).getString("message"))
                                        }
                                        MAINTENANCE.toInt()->{
                                            resource=
                                                Resource.FailureMessage(JSONObject(it).getString("message"))
                                        }
                                    }
                                }
                            }
                        }catch (e:Exception){
                            e.printStackTrace()
                            resource= Resource.Error(e)
                        }
                    }
                }
                return resource!!
            }

        }catch (e:Exception){
            e.printStackTrace()
            resource = when (e) {
                is SocketTimeoutException -> {
                    Resource.ConnectionTimeOut("Request timeout")
                }
                is ConnectException -> {
                    Resource.NoInternet("No Internet")
                }
                else -> {
                    Resource.Error(e)
                }
            }
        }

        return resource!!

    }
}