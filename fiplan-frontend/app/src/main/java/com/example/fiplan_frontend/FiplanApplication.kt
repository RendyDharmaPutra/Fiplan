package com.example.fiplan_frontend

import android.app.Application
import com.example.fiplan_frontend.core.config.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FiplanApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin() {
            androidContext(this@FiplanApplication)
            modules(appModule)
        }
    }

}