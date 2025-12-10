package com.example.saltasalta.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.ui.screens.GameMenuScreen

@Composable
fun PlayButton() {
    Box(
        modifier = Modifier
            .size(140.dp)
            .graphicsLayer {
                shadowElevation = 40f
                shape = CircleShape
                clip = false
            }
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text("JUGAR", color = Color.Black, fontSize = 20.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewGameMenuScreen() {
    PlayButton()
}