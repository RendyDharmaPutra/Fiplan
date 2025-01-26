package com.example.fiplan_frontend.core.ui.components.text_field

data class TextFieldState(
    val text: String = "",
    val isValid: Boolean = true,
    val errorMessage: String = ""
) {
    fun updateText(newText: String): TextFieldState {
        return copy(text = newText)
    }

    fun setError(message: String): TextFieldState {
        return copy(isValid = false, errorMessage = message)
    }

    fun clearError(): TextFieldState {
        return copy(isValid = true, errorMessage = "")
    }
}
