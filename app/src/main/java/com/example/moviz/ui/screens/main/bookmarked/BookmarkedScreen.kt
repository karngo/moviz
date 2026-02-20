package com.example.moviz.ui.screens.main.bookmarked

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviz.ui.components.BookmarkedMovieList
import com.example.moviz.ui.components.ToolBar

@Composable
fun BookmarkedScreen(
    viewModel: BookmarkedViewModel = hiltViewModel(),
    onMovieClick: (Long) -> Unit = {}
) {
    val bookmarkedMovies by viewModel.bookmarkedMovies.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF242A32))
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ToolBar("Bookmarks")

        BookmarkedMovieList(
            movies = bookmarkedMovies,
            onClick = onMovieClick,
            onRemoveBookmark = { movieId ->
                viewModel.removeBookmark(movieId)
            }
        )
    }
}

@Preview
@Composable
fun PreviewWatchlistScreen() {
    BookmarkedScreen()
}