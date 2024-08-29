package com.example.rentalcarsapp

import android.app.Application
import com.example.rentalcarsapp.VIewModel.AuthViewModel
import com.example.rentalcarsapp.VIewModel.CarSearchViewModel
import com.example.rentalcarsapp.VIewModel.LuxCarsViewModel
import com.example.rentalcarsapp.VIewModel.VipCarsViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { FirebaseAuth.getInstance() }
    viewModel { LuxCarsViewModel() }
    viewModel { CarSearchViewModel() }
    viewModel { VipCarsViewModel() }
    viewModel { AuthViewModel() }
}

