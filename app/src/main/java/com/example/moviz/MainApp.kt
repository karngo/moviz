package com.example.moviz

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviz.ui.Destination
import com.example.moviz.ui.screens.HomeScreen
import com.example.moviz.ui.screens.MovieDetailScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Home) {
        composable<Destination.Home> {
            HomeScreen {
                navController.navigate(it)
            }
        }
        composable<Destination.MovieDetail> { MovieDetailScreen() }
    }
}