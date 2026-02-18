package com.example.moviz.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.moviz.ui.Destination

@Composable
fun AppScreen() {
    val navController = rememberNavController()

    MaterialTheme {
        AppNavHost(navController, startDestination = Destination.Main)
    }
}