package com.example.moviz.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviz.ui.MainDestination
import com.example.moviz.ui.screens.main.bookmarked.BookmarkedScreen
import com.example.moviz.ui.screens.main.home.HomeScreen
import com.example.moviz.ui.screens.main.search.SearchScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: MainDestination,
    onViewMovie: () -> Unit
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<MainDestination.Home> { HomeScreen { onViewMovie() } }
        composable<MainDestination.Search> { SearchScreen() }
        composable<MainDestination.Bookmarked> { BookmarkedScreen() }
    }
}