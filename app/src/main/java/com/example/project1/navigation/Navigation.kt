package com.example.project1.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project1.screens.HomeScreen
import com.example.project1.screens.LoginScreen
import com.example.project1.screens.SignUpScreen
import com.example.project1.viewmodel.AuthViewModel

@Composable
fun Navigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(
            route = "login",
            enterTransition = {
                slideInVertically()
            },
            exitTransition = {
                slideOutVertically()
            }
        ) {
            when (authViewModel.authState.value) {
                is AuthViewModel.AuthState.Authenticated -> navController.navigate("home")
                is AuthViewModel.AuthState.UnAuthenticated -> {
                    LoginScreen(navController = navController, authViewModel = authViewModel)
                }
                else -> Unit
            }
        }
        composable(
            route = "signup",
            enterTransition = {
                slideInVertically()
            },
            exitTransition = {
                slideOutVertically()
            }
        ) {
            SignUpScreen(navController, authViewModel)
        }
        composable(
            route = "home",
            enterTransition = {
                slideInVertically()
            },
            exitTransition = {
                slideOutVertically()
            }
        ) {
            HomeScreen(navController, authViewModel)
        }
    }
}
