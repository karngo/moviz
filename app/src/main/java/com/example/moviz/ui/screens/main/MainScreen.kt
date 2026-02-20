package com.example.moviz.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviz.R
import com.example.moviz.ui.MainDestination
import com.example.moviz.ui.screens.main.bookmarked.BookmarkedScreen
import com.example.moviz.ui.screens.main.home.HomeScreen
import com.example.moviz.ui.screens.main.search.SearchScreen

@Composable
fun MainScreen(onViewMovie: (Long) -> Unit) {
    val navController = rememberNavController()
    val startDestination: MainDestination = MainDestination.Home
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: startDestination::class.qualifiedName

    val navBarItemColors = NavigationBarItemDefaults.colors(
        indicatorColor = Color.Transparent,
        selectedIconColor = Color(0xFF0296E5),
        selectedTextColor = Color(0xFF0296E5),
        unselectedIconColor = Color(0xFF67686D),
        unselectedTextColor = Color(0xFF67686D)
    )

    fun moveToTab(destination: MainDestination) {
        navController.navigate(destination) {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = Color(0xFF242A32),
                tonalElevation = 0.dp,
                modifier = Modifier.drawBehind {
                    val borderColor = Color(0xFF0296E5)
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 4f
                    )
                }) {
                NavigationBarItem(
                    selected = selectedDestination == MainDestination.Home::class.qualifiedName,
                    onClick = {
                        moveToTab(MainDestination.Home)
                    },
                    icon = {
                        Icon(painterResource(R.drawable.ic_home), contentDescription = "Home")
                    },
                    label = {
                        Text("Home")
                    },
                    colors = navBarItemColors
                )

                NavigationBarItem(
                    selected = selectedDestination == MainDestination.Search::class.qualifiedName,
                    onClick = {
                        moveToTab(MainDestination.Search)
                    },
                    icon = {
                        Icon(
                            painterResource(R.drawable.ic_search),
                            contentDescription = "Search Movies"
                        )
                    },
                    label = {
                        Text("Search")
                    },
                    colors = navBarItemColors
                )

                NavigationBarItem(
                    selected = selectedDestination == MainDestination.Bookmarked::class.qualifiedName,
                    onClick = {
                        moveToTab(MainDestination.Bookmarked)
                    },
                    icon = {
                        Icon(
                            painterResource(R.drawable.ic_bookmark),
                            contentDescription = "Bookmarked Movies",
                        )
                    },
                    label = {
                        Text("Bookmarks")
                    },
                    colors = navBarItemColors
                )
            }
        }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavHost(navController = navController, startDestination = startDestination) {
                composable<MainDestination.Home> {
                    HomeScreen(
                        onSelectMovie = { onViewMovie(it) },
                        onNavigate = { moveToTab(it) }
                    )
                }
                composable<MainDestination.Search> {
                    SearchScreen {
                        onViewMovie(it)
                    }
                }
                composable<MainDestination.Bookmarked> { BookmarkedScreen() }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(onViewMovie = { })
}