package com.example.fiplan_frontend.core.ui.components.text_field.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme

@Composable
fun TextFieldCompose(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    singleLine: Boolean = true,
    isError: Boolean,
    errorMessage: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = label, style = MaterialTheme.typography.labelMedium)

        OutlinedTextField(
            placeholder = {
                Text("Masukkan ${label.lowercase()} anda")
            },

            value = value,
            onValueChange = onChange,

            singleLine = singleLine,
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,

            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.background,

                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,

                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier
                        .fillMaxWidth()
                        .border(0.5.dp, MaterialTheme.colorScheme.outline),
        )

         if (isError)
            Text(text = errorMessage, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onError)
    }
}

@Preview(showBackground = true)
@Composable
fun  TextFieldComposePreview() {
    FiplanfrontendTheme {

        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
                .fillMaxSize(),
        ) {

        TextFieldCompose(
            label = "Username",
            value = "",
            onChange = {},
            isError = true,
            errorMessage = "Example Error Message",
        )
        }

    }

}
