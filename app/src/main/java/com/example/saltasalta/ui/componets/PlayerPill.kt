package com.example.saltasalta.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayerPill(
    text: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .background(Color(0xFF2A2A2A), shape = RoundedCornerShape(14.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewPlayerPill() {
    Column(modifier = Modifier.padding(20.dp)) {
        // Ejemplo 1: Nombre normal
        PlayerPill(text = "Pepe123")

        Spacer(modifier = Modifier.height(10.dp))

        // Ejemplo 2: Nombre largo
        PlayerPill(text = "Momo_de_twice_Super_Fan")
    }
}

