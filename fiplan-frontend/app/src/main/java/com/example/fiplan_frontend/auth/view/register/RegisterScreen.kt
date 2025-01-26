package com.example.fiplan_frontend.view.auth.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiplan_frontend.core.ui.components.dialog.BaseDialog
import com.example.fiplan_frontend.core.ui.components.text_field.TextFieldState
import com.example.fiplan_frontend.core.ui.theme.FiplanfrontendTheme
import com.example.fiplan_frontend.auth.view.components.AuthAction
import com.example.fiplan_frontend.auth.view.components.AuthForm
import com.example.fiplan_frontend.auth.view.components.AuthHeader

@Composable
fun RegisterScreen(
    usernameState: TextFieldState,
    onUsernameChange: (value: String) -> Unit,
    passwordState: TextFieldState,
    onPasswordChange: (value: String) -> Unit,
    isLoading: Boolean,
    message: String?,
    error: String?,

    submitAction: () -> Unit,
    clearMessage: () -> Unit,
    navigate: () -> Unit
) {

    Scaffold { paddingValues ->
        if(message != null) {
            BaseDialog(
                title = message,
                content = error ?: "Silahkan Login",
            ) {
                clearMessage()
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .wrapContentHeight(Alignment.CenterVertically)

        ) {
            AuthHeader("Registrasi Diri Anda")

            AuthForm(
                usernameState = usernameState,
                onUsernameChange = onUsernameChange,
                passwordState = passwordState,
                onPasswordChange = onPasswordChange,
                buttonText = "Kirim",

                isLoading = isLoading,

                submitAction = submitAction
            )

            AuthAction(
                question = "Sudah punya akun?",
                guidance = "Login disini",
                action = {
                    navigate()
                }
            )

        }


    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun  RegisterScreenPreview() {
    FiplanfrontendTheme {
        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            RegisterScreen (
                usernameState = TextFieldState(),
                onUsernameChange = {},

                passwordState = TextFieldState(),
                onPasswordChange = {},

                isLoading = false,
                message = null,
                error = null,

                submitAction = {},
                clearMessage = {},
                navigate = {}
            )
        }
    }
}