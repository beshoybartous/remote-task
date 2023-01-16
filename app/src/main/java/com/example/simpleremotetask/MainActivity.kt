package com.example.simpleremotetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.simpleremotetask.navigation.SetupNavGraph
import com.example.simpleremotetask.ui.theme.SimpleRemoteTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SimpleRemoteTaskTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}