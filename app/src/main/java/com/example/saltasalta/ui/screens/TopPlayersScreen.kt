package com.example.saltasalta.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.ui.componets.LoginAvatar
import com.example.saltasalta.ui.componets.PlayerPill

@Composable
fun TopPlayersScreen(
    onBackClick: () -> Unit = {}
) {
    val players = listOf(
        "Pepe123",
        "Momo_de_twice",
        "PaolitaS",
        "ChicoBTS",
        "PapaRuffle"
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F))
    ) {
        val screenHeight = maxHeight
        val screenWidth = maxWidth

        // Botón back superior centrado
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.TopCenter)
                .size(48.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Regresar",
                tint = Color.Black
            )
        }

        // Card con el top
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "TOP\nJUGADORES",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                players.forEachIndexed { index, player ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${index + 1}-",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        PlayerPill(
                            text = player,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Montañas inferiores
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.28f)
                .align(Alignment.BottomCenter)
        ) {
            val terrainColor = Color(0xFF1A1A1A)
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width * 0.12f, size.height * 0.55f)
                lineTo(size.width * 0.32f, size.height * 0.9f)
                lineTo(size.width * 0.52f, size.height * 0.5f)
                lineTo(size.width * 0.75f, size.height * 0.85f)
                lineTo(size.width, size.height * 0.6f)
                lineTo(size.width, size.height)
                close()
            }
            drawPath(path, terrainColor)
        }

        // Avatares sobre picos
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            LoginAvatar(modifier = Modifier.size(90.dp))
            LoginAvatar(modifier = Modifier.size(90.dp))
            LoginAvatar(modifier = Modifier.size(90.dp))
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun PreviewTopPlayersScreen() {
    TopPlayersScreen()
}

