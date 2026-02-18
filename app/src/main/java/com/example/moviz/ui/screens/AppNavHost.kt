package com.example.moviz.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviz.ui.Destination
import com.example.moviz.ui.screens.main.MainScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Destination.Main> {
            MainScreen {
                navController.navigate(Destination.MovieDetail)
            }
        }
        composable<Destination.MovieDetail> { MovieDetailScreen() }
    }
}