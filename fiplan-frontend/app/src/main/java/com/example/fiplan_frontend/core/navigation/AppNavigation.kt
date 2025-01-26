package com.example.fiplan_frontend.core.navigation

import DataStoreManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiplan_frontend.auth.view.login.LoginScreen
import com.example.fiplan_frontend.view.auth.login.RegisterScreen
import com.example.fiplan_frontend.auth.viewmodel.form.RegisterFormViewModel
import com.example.fiplan_frontend.auth.viewmodel.AuthViewModel
import com.example.fiplan_frontend.auth.viewmodel.form.LoginFormViewModel
import com.example.fiplan_frontend.home.view.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AppNavigation(navController: NavHostController) {
    val dataStore: DataStoreManager = koinInject()

    val isLoggedIn = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        dataStore.token.collect { token ->
            isLoggedIn.value = token != null
        }
    }


    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn.value) "home" else "login"
    ) {
        composable("login") {
            val authViewModel: AuthViewModel = koinViewModel()
            val loginFormViewModel: LoginFormViewModel = koinViewModel()

            val usernameState by loginFormViewModel.usernameState.collectAsStateWithLifecycle()
            val passwordState by loginFormViewModel.passwordState.collectAsStateWithLifecycle()

            val isLoading by authViewModel.isLoading.collectAsStateWithLifecycle()
            val message by authViewModel.message.collectAsStateWithLifecycle()
            val error by authViewModel.error.collectAsStateWithLifecycle()

            LoginScreen(
                usernameState = usernameState,
                onUsernameChange = { value -> loginFormViewModel.onUsernameChange(value) },
                passwordState = passwordState,
                onPasswordChange = { value -> loginFormViewModel.onPasswordChange(value) },
                isLoading = isLoading,
                message = message,
                error = error,

                submitAction = {
                    authViewModel.login(usernameState.text, passwordState.text)
                },
                clearMessage = { authViewModel.clearMessage() },
                navigate = {
                    navController.navigate("register") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            val authViewModel: AuthViewModel = koinViewModel()
            val regsterViewModel: RegisterFormViewModel = koinViewModel()

            val usernameState by regsterViewModel.usernameState.collectAsStateWithLifecycle()
            val passwordState by regsterViewModel.passwordState.collectAsStateWithLifecycle()

            val isLoading by authViewModel.isLoading.collectAsStateWithLifecycle()
            val message by authViewModel.message.collectAsStateWithLifecycle()
            val error by authViewModel.error.collectAsStateWithLifecycle()

            RegisterScreen(
                usernameState = usernameState,
                onUsernameChange = { value -> regsterViewModel.onUsernameChange(value) },
                passwordState = passwordState,
                onPasswordChange = { value -> regsterViewModel.onPasswordChange(value) },
                isLoading = isLoading,
                message = message,
                error = error,

                submitAction = {
                    if(regsterViewModel.isFormValid())
                        authViewModel.register(usernameState.text, passwordState.text)
                },
                clearMessage = { authViewModel.clearMessage() },
                navigate = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen()
        }
    }

}