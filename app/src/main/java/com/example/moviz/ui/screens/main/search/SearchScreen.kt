package com.example.moviz.ui.screens.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviz.R
import com.example.moviz.ui.components.MovieList
import com.example.moviz.ui.components.SearchTab
import com.example.moviz.ui.components.ToolBar

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(), onViewMovie: () -> Unit) {
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#242A32".toColorInt())),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ToolBar("Search", trailingIcon = R.drawable.ic_info)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            SearchTab { searchText ->
                viewModel.searchMovies(searchText)
            }
        }

        MovieList(
            searchResults,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            onViewMovie()
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen {}
}