package com.example.moviz.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun Tabs(tabItems: List<String>, activeTab: String, onChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(tabItems) { item ->
                Column(
                    modifier = Modifier
                        .clickable(onClick = {
                            onChange(item)
                        })
                        .drawBehind {
                            if (activeTab != item)
                                return@drawBehind

                            val strokeWidth = 3.dp.toPx()
                            val y = size.height - strokeWidth / 2

                            drawLine(
                                color = Color("#3A3F47".toColorInt()),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        }
                        .padding(vertical = 12.dp)
                ) {
                    Text(item, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTabs() {
    Tabs(listOf("Tab1", "Tab2", "Tab3"), activeTab = "Tab2") {}
}