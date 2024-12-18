package com.example.planperfect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavigation()
            }
        }
    }
}

enum class AppScreen(val route: String) {
    Login("login"),
    SignUp("signup"),
    Settings("settings");

    companion object {
        fun fromRoute(route: String?): AppScreen =
            when (route?.substringBefore("/")) {
                Login.route -> Login
                SignUp.route -> SignUp
                Settings.route -> Settings
                null -> throw IllegalArgumentException("Route cannot be null")
                else -> throw IllegalArgumentException("Unknown route: $route")
            }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ) {
        composable(AppScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(AppScreen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(AppScreen.Settings.route) {
            SettingScreen(navController)
        }
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            navController.navigate(AppScreen.Settings.route)
        }
    }
}