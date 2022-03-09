package com.cornellappdev.rideshare

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cornellappdev.rideshare.google.GoogleUserModel
import com.cornellappdev.rideshare.ui.theme.RideshareTheme
import com.squareup.moshi.Moshi

@Composable
fun NavigationController(){
    val navController = rememberNavController()
    
    RideshareTheme() {
        NavHost(navController = navController, startDestination = "auth"){
            composable(Destinations.Auth) { GoogleSignInView(navController) }
            composable(Destinations.Home) { backStackEntry ->
                val userJson = backStackEntry.arguments?.getString("user")

                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(GoogleUserModel::class.java)
                val userObject = jsonAdapter.fromJson(userJson!!)

            }
        }
    }
}