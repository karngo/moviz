package com.example.moviz.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.moviz.R
import com.example.moviz.ui.MainDestination

@Composable
fun MainScreen(onViewMovie: () -> Unit) {
    val navController = rememberNavController()
    val startDestination: MainDestination = MainDestination.Home
    var selectedDestination by remember { mutableStateOf(startDestination) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                NavigationBarItem(
                    selected = selectedDestination == MainDestination.Home,
                    onClick = {
                        navController.navigate(route = MainDestination.Home)
                        selectedDestination = MainDestination.Home
                    },
                    icon = {
                        Icon(
                            painterResource(R.drawable.ic_home),
                            contentDescription = "Home"
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedDestination == MainDestination.Search,
                    onClick = {
                        navController.navigate(route = MainDestination.Search)
                        selectedDestination = MainDestination.Search
                    },
                    icon = {
                        Icon(
                            painterResource(R.drawable.ic_search),
                            contentDescription = "Search Movies"
                        )
                    }
                )

                NavigationBarItem(
                    selected = selectedDestination == MainDestination.Bookmarked,
                    onClick = {
                        navController.navigate(route = MainDestination.Bookmarked)
                        selectedDestination = MainDestination.Bookmarked
                    },
                    icon = {
                        Icon(
                            painterResource(R.drawable.ic_bookmark),
                            contentDescription = "Bookmarked Movies"
                        )
                    }
                )
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            MainNavHost(navController, startDestination, onViewMovie = onViewMovie)
        }
    }
}