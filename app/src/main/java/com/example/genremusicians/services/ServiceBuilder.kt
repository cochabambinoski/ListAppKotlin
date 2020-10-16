package com.example.genremusicians.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceBuilder {
    private var retrofit:Retrofit?=null
    private var logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private var okHttp = OkHttpClient.Builder().addInterceptor(logger).build()

    private fun getClientScalar(baseUrl:String="https://dog.ceo/api/"):Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    private fun getClientScalar2(baseUrl:String="https://www.thesportsdb.com/api/"):Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    private fun getClientGson(baseUrl:String="https://dog.ceo/api/"):Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    private fun getClientGson2(baseUrl:String="https://dog.ceo/api/"):Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    fun <T> buildServiceScalar(contract: Class<T>): T{
        return getClientScalar().create(contract)
    }

    fun <T> buildServiceScalar2(contract: Class<T>): T{
        return getClientScalar2().create(contract)
    }

    fun <T> buildService(contract: Class<T>): T{
        return getClientGson().create(contract)
    }

    fun <T> buildService2(contract: Class<T>): T{
        return getClientGson().create(contract)
    }
}