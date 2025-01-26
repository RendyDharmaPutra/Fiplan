package com.example.fiplan_frontend.auth.viewmodel

import AuthRepository
import DataStoreManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiplan_frontend.core.api.RepositoryResponseModel
import com.example.fiplan_frontend.auth.model.AuthRequestModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val dataStoreManager: DataStoreManager,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    fun clearMessage() {
        _message.value = null

        if(!_error.value.isNullOrEmpty()) {
            _error.value = null
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true

            when (val result = authRepository.login(AuthRequestModel(username, password))) {
                is RepositoryResponseModel.Success -> {
                    result.data?.token.let { token ->
                        if (token != null) {
                            dataStoreManager.saveToken(token)
                        }
                    }
                    _message.value = result.message
                }
                is RepositoryResponseModel.Error -> {
                    _message.value = result.message
                    _error.value = result.error
                }
            }

            _isLoading.value = false
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true

            when (val result = authRepository.register(AuthRequestModel(username, password))) {
                is RepositoryResponseModel.Success -> {
                    _message.value = result.message
                }
                is RepositoryResponseModel.Error -> {
                    _message.value = result.message
                    _error.value = result.error
                }
            }

            _isLoading.value = false
        }

    }

}