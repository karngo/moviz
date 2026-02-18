package com.example.moviz.ui.screens.main.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Search Screen")
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}