package com.example.moviz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviz.R
import com.example.moviz.ui.model.MovieDetail

@Composable
fun MovieGrid(movies: List<MovieDetail>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(movies) {
            Image(
                painter = painterResource(R.drawable.dummy_movie),
                contentDescription = "Movie Card",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Preview
@Composable
fun PreviewMovieGrid() {
    MovieGrid(
        listOf(
            MovieDetail(""),
            MovieDetail(""),
            MovieDetail(""),
            MovieDetail(""),
            MovieDetail("")
        )
    )
}