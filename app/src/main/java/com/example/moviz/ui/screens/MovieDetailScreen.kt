package com.example.moviz.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.moviz.R
import com.example.moviz.ui.components.IconText
import com.example.moviz.ui.components.Rating
import com.example.moviz.ui.components.Tabs

@Composable
fun MovieDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#242A32".toColorInt()))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Back Button"
            )

            Text("Detail", color = Color.White, fontSize = 24.sp)

            Image(
                painter = painterResource(R.drawable.ic_bookmark),
                contentDescription = "Bookmark",
                modifier = Modifier.size(32.dp)
            )
        }

        Box {
            Image(
                painter = painterResource(R.drawable.dummy_movie_poster),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp))
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(30))
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Rating("9.3")
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 24.dp)
                    .height(140.dp)
                    .offset(y = 70.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.dummy_movie),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                )
                Column {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "Spiderman No Way Home",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 24.dp)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 84.dp)
                .height(IntrinsicSize.Max)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconText(R.drawable.ic_calendar, "2021")
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.Gray)
            ) {}
            IconText(R.drawable.ic_clock, "148 Minutes")
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.Gray)
            ) {}
            IconText(R.drawable.ic_ticket, "Action")
            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp)
        ) {
            Tabs(listOf("About Movie", "Reviews", "Cast"), "About Movie") { }
        }
    }
}

@Preview
@Composable
fun PreviewMovieDetailScreen() {
    MovieDetailScreen()
}
