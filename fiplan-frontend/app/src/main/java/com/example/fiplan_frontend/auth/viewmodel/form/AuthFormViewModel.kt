package com.example.fiplan_frontend.auth.viewmodel.form

import androidx.lifecycle.ViewModel
import com.example.fiplan_frontend.core.ui.components.text_field.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AuthFormViewModel : ViewModel() {
    protected val _usernameState = MutableStateFlow(TextFieldState())
    val usernameState = _usernameState.asStateFlow()

    protected val _passwordState = MutableStateFlow(TextFieldState())
    val passwordState = _passwordState.asStateFlow()

    open fun onUsernameChange(newUsername: String) {
        _usernameState.value = _usernameState.value.copy(text = newUsername)
    }

    open fun onPasswordChange(newPassword: String) {
        _passwordState.value = _passwordState.value.copy(text = newPassword)
    }
}