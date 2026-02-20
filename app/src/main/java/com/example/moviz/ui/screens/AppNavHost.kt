package com.example.moviz.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.moviz.ui.Destination
import com.example.moviz.ui.screens.main.MainScreen
import com.example.moviz.ui.screens.moviedetail.MovieDetailScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Destination.Main> {
            MainScreen {
                Log.d("navigation", "movieId: $it")
                navController.navigate(Destination.Detail(it))
            }
        }

        composable<Destination.Detail> {
            MovieDetailScreen(movieId = it.toRoute<Destination.Detail>().movieId)
        }
    }
}