package com.example.simpleremotetask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.simpleremotetask.features.login.presentation.ui.LoginScreen
import com.example.simpleremotetask.features.medicine.presentation.details.ui.DetailsScreen
import com.example.simpleremotetask.features.medicine.presentation.home.ui.HomeScreen
import com.example.simpleremotetask.utils.Constants

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login
        .route, builder = {
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.Home.route, arguments = listOf(
                navArgument(Constants.USER_NAME_ARGUMENT_KEY) {
                    type = NavType.StringType
                })
        ) {
            HomeScreen(navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Details.route, arguments = listOf(
                navArgument(Constants.DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                })
        ) {
            DetailsScreen()
        }
    })

}