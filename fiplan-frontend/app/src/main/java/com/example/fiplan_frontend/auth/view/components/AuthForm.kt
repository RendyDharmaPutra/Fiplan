package com.example.fiplan_frontend.auth.view.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiplan_frontend.core.ui.components.button.SubmitButtonCompose
import com.example.fiplan_frontend.core.ui.components.text_field.TextFieldState
import com.example.fiplan_frontend.core.ui.components.text_field.components.PasswordFieldCompose
import com.example.fiplan_frontend.core.ui.components.text_field.components.TextFieldCompose
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme


@Composable
fun AuthForm(
    usernameState: TextFieldState,
    onUsernameChange: (value: String) -> Unit,
    passwordState: TextFieldState,
    onPasswordChange: (value: String) -> Unit,
    buttonText: String,

    isLoading: Boolean = false,

    submitAction: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextFieldCompose(
            label = "Username",
            value = usernameState.text,
            onChange = { onUsernameChange(it) },
            isError = !usernameState.isValid,
            errorMessage = usernameState.errorMessage,
        )

        PasswordFieldCompose(
            label = "Password",
            value = passwordState.text,
            onChange = { onPasswordChange(it) },
            isError = !passwordState.isValid,
            errorMessage = passwordState.errorMessage,
        )

        Spacer(Modifier.height(4.dp))

        SubmitButtonCompose(text = buttonText, isLoading = isLoading) {
            submitAction()
        }
    }
}



@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun  AuthFormPreview() {
    FiplanfrontendTheme {
        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
                .fillMaxSize(),
        ) {
            AuthForm(
                usernameState = TextFieldState(),
                onUsernameChange = {},

                passwordState = TextFieldState(),
                onPasswordChange = {},

                buttonText = "Login",

                isLoading = false,

                submitAction = {}
            )
        }
    }
}
