package com.example.moviz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviz.R

@Composable
fun SearchDisplay(onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3A3F47), RoundedCornerShape(40))
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .clickable(onClick = onClick)
    ) {
        Text(text = "Search", color = Color(0xFF67686D))

        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "Search Movie",
            colorFilter = ColorFilter.tint(Color(0xFF67686D))
        )
    }
}

@Preview
@Composable
fun PreviewSearchDisplay() {
    Box(modifier = Modifier.padding(32.dp)) {
        SearchDisplay()
    }
}