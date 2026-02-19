package com.example.moviz.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviz.ui.MainDestination
import com.example.moviz.ui.components.MovieGrid
import com.example.moviz.ui.components.SearchDisplay
import com.example.moviz.ui.components.Tabs

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSelectMovie: () -> Unit,
    onNavigate: (MainDestination) -> Unit
) {
    var activeTab by remember { mutableStateOf("Now Playing") }
    val nowPlaying = viewModel.nowPlaying.collectAsLazyPagingItems()
    val trending = viewModel.trending.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#242A32".toColorInt()))
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            "What do you want to watch?",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500
        )

        SearchDisplay {
            onNavigate(MainDestination.Search)
        }

        Tabs(
            listOf("Now Playing", "Trending"),
            activeTab = activeTab,
            onChange = { activeTab = it })

        MovieGrid(if (activeTab == "Trending") trending else nowPlaying) {
            onSelectMovie()
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(onSelectMovie = {}, onNavigate = {})
}