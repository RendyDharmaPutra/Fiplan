package com.example.fiplan_frontend.core.config.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun createApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://172.17.69.101:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}