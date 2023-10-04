package com.trioangle.systemtask.module

import com.trioangle.systemtask.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)

        val client = httpClient.build()
        return Retrofit.Builder()
              .baseUrl("https://api.instantwebtools.net/")
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build()


    }

    @NewRetrofit
    @Provides
    fun provideWebAPiServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }


}