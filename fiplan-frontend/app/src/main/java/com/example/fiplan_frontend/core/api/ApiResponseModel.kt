package com.example.fiplan_frontend.core.api

data class ApiResponseModel<T>(
    val status: String,
    val message: String,
    val error: Any? = null,
    val data: T? = null
)
