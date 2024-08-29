package com.example.rentalcarsapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rentalcarsapp.Model.AuthState
import com.example.rentalcarsapp.Screens.AuthScreen
import com.example.rentalcarsapp.Screens.BookCar
import com.example.rentalcarsapp.Screens.HomeScreen
import com.example.rentalcarsapp.Screens.LuxCarsDetailsScreen
import com.example.rentalcarsapp.Screens.MainScreen
import com.example.rentalcarsapp.Screens.ResetPasswordScreen
import com.example.rentalcarsapp.Screens.SignIn
import com.example.rentalcarsapp.Screens.SignUp
import com.example.rentalcarsapp.Screens.VipCarsDetailsScreen
import com.example.rentalcarsapp.VIewModel.AuthViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val auth = authViewModel.authState.observeAsState()

    NavHost(
        navController = navController,
        startDestination = if (auth.value is AuthState.Authenticated) "home" else "main"
    ) {
        composable(
            "carDetails/{carType}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("carType") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")
            val carType = it.arguments?.getString("carType")
            if (id != null && carType != null) {
                when (carType) {
                    "Lux" -> LuxCarsDetailsScreen(navController = navController, carId = id)
                    "Vip" -> VipCarsDetailsScreen(navController = navController, carId = id)
                }
            }
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("auth") {
            AuthScreen(navController = navController)
        }
        composable("signIn") {
            SignIn(navController = navController)
        }
        composable("signUp") {
            SignUp(navController = navController)
        }
        composable("resetPassword") {
            ResetPasswordScreen(navController = navController)
        }
        composable("bookCar") {
            BookCar(navController = navController)
        }

    }

}