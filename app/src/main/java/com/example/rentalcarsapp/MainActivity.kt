package com.example.rentalcarsapp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.rentalcarsapp.Navigation.Navigation
import com.example.rentalcarsapp.ui.theme.RentalCarsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        CoroutineScope(Dispatchers.Main).launch {
            splashScreen.setKeepOnScreenCondition { false }
        }

        enableEdgeToEdge()
        setContent {
            RentalCarsAppTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.isSystemBarsVisible = false
                systemUiController.isNavigationBarVisible = false
                Navigation()
            }
        }
    }
}