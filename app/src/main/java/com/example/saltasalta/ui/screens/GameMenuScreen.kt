package com.example.saltasalta.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.ui.componets.LoginAvatar
import com.example.saltasalta.ui.componets.MenuIcon

@Composable
fun GameMenuScreen(
    onPlayClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onTopClick: () -> Unit = {},
    bestScore: Int = 0
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F)) // Fondo negro/gris muy oscuro
    ) {
        val screenHeight = maxHeight
        val screenWidth = maxWidth

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            // Iconos superiores (Menú y Perfil)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween // Separa los iconos a los extremos
            ) {
                Box(modifier = Modifier.clickable { onProfileClick() }) {
                    MenuIcon(text = "PERFIL", "\uD83D\uDC64")
                }
                Box(modifier = Modifier.clickable { onTopClick() }) {
                    MenuIcon(text = "TOP", "🏆")
                }
            }

            Spacer(Modifier.height(screenHeight * 0.05f))

            // Avatar central y base
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Si LoginAvatar da error, asegúrate de que el archivo exista en ui/components/
                LoginAvatar(
                    modifier = Modifier.size(120.dp)
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "BLOB RUN",
                    color = Color.White,
                    fontSize = 36.sp,        // Tamaño grande
                    fontWeight = FontWeight.Bold, // Negritas
                    letterSpacing = 2.sp,    // Espaciado para estilo de juego
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Base pequeña debajo del avatar
                Box(
                    modifier = Modifier
                        .width(140.dp)
                        .height(8.dp)
                        .background(Color(0xFF404040), shape = CircleShape)
                )
            }

            Spacer(Modifier.height(screenHeight * 0.1f))

            // Botón JUGAR con efecto de brillo (Glow)
            Box(
                modifier = Modifier
                    .size(180.dp)
                    // Sombra blanca para simular brillo neon
                    .shadow(
                        elevation = 20.dp,
                        shape = CircleShape,
                        spotColor = Color.White,
                        ambientColor = Color.White
                    )
                    .background(Color.White, CircleShape)
                    .clickable { onPlayClick() }, // Ahora el botón funciona
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "JUGAR",
                    color = Color.Black,
                    fontSize = 22.sp,
                    // fontWeight = FontWeight.Bold // Opcional: poner negritas
                )
            }

            // Mostrar mejor puntaje del usuario
            Spacer(Modifier.height(16.dp))

            Text(
                text = if (bestScore > 0) "Mejor: $bestScore" else "Mejor: --",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Montañas en la parte inferior
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.22f)
                .align(Alignment.BottomCenter)
        ) {
            val terrainColor = Color(0xFF1A1A1A)
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width * 0.12f, size.height * 0.65f)
                lineTo(size.width * 0.32f, size.height * 0.9f)
                lineTo(size.width * 0.55f, size.height * 0.6f)
                lineTo(size.width * 0.78f, size.height * 0.88f)
                lineTo(size.width, size.height * 0.7f)
                lineTo(size.width, size.height)
                close()
            }
            drawPath(path, terrainColor)
        }
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun PreviewGameMenuScreen() {
    GameMenuScreen()
}