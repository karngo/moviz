package com.example.moviz.ui.screens.main.bookmarked

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookmarkedScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Bookmarked Screen")
    }
}

@Preview
@Composable
fun BookmarkedScreenPreview() {
    BookmarkedScreen()
}