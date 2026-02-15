package com.example.moviz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.moviz.R

@Composable
fun Rating(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = "Rating",
            colorFilter = ColorFilter.tint(Color("#FF8700".toColorInt()))
        )
        Text(
            rating,
            color = Color("#FF8700".toColorInt()),
            fontSize = 20.sp,
            fontWeight = FontWeight.W500
        )
    }
}

@Preview
@Composable
fun PreviewRating() {
    Rating("9.5")
}
