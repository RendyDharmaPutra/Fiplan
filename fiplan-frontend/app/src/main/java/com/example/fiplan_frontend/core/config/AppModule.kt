package com.example.fiplan_frontend.core.config

import AuthRepository
import DataStoreManager
import com.example.fiplan_frontend.core.config.service.ApiClient
import com.example.fiplan_frontend.auth.viewmodel.form.RegisterFormViewModel
import com.example.fiplan_frontend.auth.viewmodel.AuthViewModel
import com.example.fiplan_frontend.auth.viewmodel.form.LoginFormViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApiClient.createApiService() }
    single { DataStoreManager(androidContext()) }

    single { AuthRepository(get()) }

    viewModel { AuthViewModel(get(), get()) }
    viewModel { LoginFormViewModel() }
    viewModel { RegisterFormViewModel() }
}