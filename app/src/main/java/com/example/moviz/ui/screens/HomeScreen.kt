package com.example.moviz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.moviz.data.ApiRepository
import com.example.moviz.ui.components.MovieGrid
import com.example.moviz.ui.components.SearchTab
import com.example.moviz.ui.components.Tabs
import com.example.moviz.ui.model.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(apiRepository: ApiRepository) {
    var activeTab by remember { mutableStateOf("Now Playing") }
    var movies by remember { mutableStateOf(emptyList<MovieDetail>()) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            apiRepository.getNowPlaying().map {
                MovieDetail(imageUrl = "https://image.tmdb.org/t/p/w500${it.posterPath}")
            }.let { movies = it }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#242A32".toColorInt()))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            "What do you want to watch?",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500
        )

        SearchTab { }

        Tabs(
            listOf("Now Playing", "Trending"),
            activeTab = activeTab,
            onChange = { activeTab = it })

        MovieGrid(movies)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
//    HomeScreen()
}