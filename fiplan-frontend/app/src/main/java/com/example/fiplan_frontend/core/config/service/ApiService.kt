package com.example.fiplan_frontend.core.config.service

import com.example.fiplan_frontend.core.api.ApiResponseModel
import com.example.fiplan_frontend.auth.model.AuthRequestModel
import com.example.fiplan_frontend.auth.model.login.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body request: AuthRequestModel): Response<ApiResponseModel<Unit>>

    @POST("auth/login")
    suspend fun login(@Body request: AuthRequestModel): Response<ApiResponseModel<LoginResponseModel>>
}