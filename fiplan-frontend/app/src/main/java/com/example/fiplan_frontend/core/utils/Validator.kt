package com.example.fiplan_frontend.core.utils

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String
)

object Validator {
    fun validateNotEmpty(value: String, errorMessage: String = "Field tidak boleh kosong"): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult(false, errorMessage)
        } else {
            ValidationResult(true, "")
        }
    }

    fun validateMaxLength(value: String, length: Int, errorMessage: String = "Maximal $length karakter"): ValidationResult {
        return if (value.length > length) {
            ValidationResult(false, errorMessage)
        } else {
            ValidationResult(true, "")
        }
    }

    fun validateMinLength(value: String, length: Int, errorMessage: String = "Minimal $length karakter"): ValidationResult {
        return if (value.length < length) {
            ValidationResult(false, errorMessage)
        } else {
            ValidationResult(true, "")
        }
    }

    fun multiValidate(vararg validators: ValidationResult): ValidationResult {
        for (validator in validators) {
            if (!validator.isValid) {
                return validator
            }
        }
        return ValidationResult(true, "")
    }

}

