package com.example.moviz.ui.screens

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
            MainScreen { id, allow ->
                navController.navigate(Destination.Detail(id, allow))
            }
        }

        composable<Destination.Detail> {
            val route = it.toRoute<Destination.Detail>()
            MovieDetailScreen(movieId = route.movieId, allowBookmark = route.allowBookmark)
        }
    }
}