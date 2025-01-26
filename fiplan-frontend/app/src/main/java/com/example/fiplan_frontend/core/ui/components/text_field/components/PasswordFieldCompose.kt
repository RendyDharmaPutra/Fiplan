package com.example.fiplan_frontend.core.ui.components.text_field.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme

@Composable
fun PasswordFieldCompose(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    isError: Boolean,
    errorMessage: String = ""
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextFieldCompose(
        label = label,
        value = value,
        onChange = onChange,
        isError = isError,
        errorMessage = errorMessage,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                Icon(
                    imageVector = image,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun  PasswordFieldComposePreview() {
    FiplanfrontendTheme {

        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
                .fillMaxSize(),
        ) {

            PasswordFieldCompose(
                label = "Username",
                value = "",
                onChange = {},
                isError = true,
                errorMessage = "Example Error Message",
            )
        }

    }

}