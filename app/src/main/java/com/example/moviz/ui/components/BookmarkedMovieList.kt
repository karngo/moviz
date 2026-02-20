package com.example.moviz.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.moviz.R
import com.example.moviz.ui.model.MovieDetail

@Composable
fun BookmarkedMovieList(
    movies: List<MovieDetail>,
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit,
    onRemoveBookmark: (Long) -> Unit
) {
    if (movies.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No bookmarked movies yet",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 24.dp)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onClick(movie.id) }),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    movie.posterUrl,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(movie.title, color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Rating(movie.rating?.toString() ?: "")
                    IconText(R.drawable.ic_ticket, "Action", Color.White)
                    IconText(R.drawable.ic_calendar, movie.releaseYear, Color.White)
                    IconText(R.drawable.ic_clock, "148 Minutes", Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Remove",
                        color = Color(0xFFFF6B6B),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            onRemoveBookmark(movie.id)
                        }
                    )
                }
            }
        }
    }
}

