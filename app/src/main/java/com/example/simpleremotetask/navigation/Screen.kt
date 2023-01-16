package com.example.simpleremotetask.navigation

import com.example.simpleremotetask.utils.Constants.DETAILS_ARGUMENT_KEY

sealed class Screen(
    val route: String
) {
    object Login : Screen("login_Screen")
    object Home : Screen("home_screen")
    object Details :
        Screen("details_screen/{${DETAILS_ARGUMENT_KEY}}") {
        fun passMedicineId(medicineId: Int): String {
            return "details_screen/$medicineId"
        }
    }
}
