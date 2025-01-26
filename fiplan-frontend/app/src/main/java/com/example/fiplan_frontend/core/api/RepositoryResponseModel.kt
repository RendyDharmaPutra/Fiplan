package com.example.fiplan_frontend.core.api

sealed class RepositoryResponseModel<T> {
    data class Success<T>(val success: Boolean, val message: String, val data: T? = null) :
        RepositoryResponseModel<T>()

    data class Error<T>(val success: Boolean, val message: String, val error: String) :
        RepositoryResponseModel<T>()
}
