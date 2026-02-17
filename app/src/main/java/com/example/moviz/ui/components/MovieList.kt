package com.example.moviz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviz.R
import com.example.moviz.ui.model.MovieDetail

@Composable
fun MovieList(list: List<MovieDetail>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(list) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.dummy_movie),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Spiderman", color = Color.White, fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Rating("9.3")
                    IconText(R.drawable.ic_ticket, "Action", Color.White)
                    IconText(R.drawable.ic_calendar, "2021", Color.White)
                    IconText(R.drawable.ic_clock, "148 Minutes", Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMovieList() {
    MovieList(listOf(MovieDetail(0), MovieDetail(1), MovieDetail(2)))
}