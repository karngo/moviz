package com.example.moviz.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviz.R

@Composable
fun ToolBar(
    title: String,
    @DrawableRes trailingIcon: Int? = null,
    onBackPress: () -> Unit = {},
    onTrailClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Back Button",
                modifier = Modifier.clickable(onClick = {
                    onBackPress()
                })
            )

            if (trailingIcon != null)
                Image(
                    painter = painterResource(trailingIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(onClick = { onTrailClick() }),
                )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(title, color = Color.White, fontSize = 24.sp)
        }
    }
}


@Preview
@Composable
fun ToolBarPreview() {
    ToolBar("Toolbar", trailingIcon = R.drawable.ic_bookmark)
}

@Preview
@Composable
fun ToolBarNoTrail() {
    ToolBar("Toolbar")
}