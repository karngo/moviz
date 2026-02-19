package com.example.moviz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.moviz.R
import kotlinx.coroutines.delay

@Composable
fun SearchTab(onSearch: (searchTerm: String) -> Unit) {
    var searchInput by rememberSaveable { mutableStateOf("") }
    val bgColor = Color("#3A3F47".toColorInt())
    val textColor = Color("#67686D".toColorInt())

    LaunchedEffect(searchInput) {
        delay(500L)
        onSearch(searchInput)
    }

    BasicTextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 18.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = bgColor, shape = RoundedCornerShape(40)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    if (searchInput.isEmpty()) {
                        Text(
                            text = "Search", color = textColor
                        )
                    }
                    innerTextField()
                }

                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search Movie",
                    colorFilter = ColorFilter.tint(textColor)
                )
            }
        })
}

@Preview
@Composable
fun PreviewSearchTab() {
    Box(modifier = Modifier.padding(32.dp)) {
        SearchTab {}
    }
}