package com.example.fiplan_frontend.auth.viewmodel.form

import com.example.fiplan_frontend.core.utils.Validator

class RegisterFormViewModel: AuthFormViewModel() {

    private var isValidationActive = false

    override fun onUsernameChange(newUsername: String) {
        super.onUsernameChange(newUsername)
        if (isValidationActive)
            validateUsername()
    }

    override fun onPasswordChange(newPassword: String) {
        super.onPasswordChange(newPassword)
        if (isValidationActive)
            validatePassword()
    }

    private fun validateUsername() {
        val result = Validator.multiValidate(
            Validator.validateNotEmpty(
                value =  _usernameState.value.text,
                errorMessage =  "Username harus diisi"
            ),
            Validator.validateMinLength(
                value = _usernameState.value.text,
                length =  3,
                errorMessage = "Username minimal 3 karakter"
            ),
            Validator.validateMaxLength(
                value = _usernameState.value.text,
                length =  32, errorMessage =  "Username minimal 6 karakter"
            )
        )

        _usernameState.value = _usernameState.value.copy(isValid =  result.isValid, errorMessage = result.errorMessage)
    }

    private fun validatePassword() {
        val result = Validator.multiValidate(
            Validator.validateNotEmpty(
                value = _passwordState.value.text,
                errorMessage =  "Password harus diisi"
            ),
            Validator.validateMinLength(
                value = _passwordState.value.text,
                length = 6,
                errorMessage = "Password minimal 6 karakter"
            )
        )

        _passwordState.value = _passwordState.value.copy(isValid =  result.isValid, errorMessage = result.errorMessage)
    }


    fun isFormValid(): Boolean {
        isValidationActive = true

        validateUsername()
        validatePassword()

        return usernameState.value.isValid && passwordState.value.isValid
    }

}