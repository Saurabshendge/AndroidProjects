package com.trioangle.systemtask.module

import com.trioangle.systemtask.model.Passengers
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface WebServices {

    @GET("v1/passenger")
    suspend fun getPassenger(@Query("page") page: Int, @Query("size") size: Int ):Response<Passengers>


}