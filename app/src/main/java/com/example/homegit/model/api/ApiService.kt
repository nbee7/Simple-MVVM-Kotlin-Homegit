package com.example.homegit.model.api

import com.example.homegit.util.Const.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun <S> createService(serviceClass: Class<S>, okhttpClient: OkHttpClient): S {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okhttpClient)
                .build()

        return retrofit.create(serviceClass)
    }
}