package com.example.saltasalta.ui.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.saltasalta.ui.theme.DarkBackground


// Definimos el color rosa pálido específico de la imagen
val PalePink = Color(0xFFFFCdd2)
val GreySmile = Color(0xFF9E9E9E)

@Composable
fun LoginAvatar(
    modifier: Modifier = Modifier
) {
    // Aumentamos ligeramente el tamaño base para que los elementos grandes quepan bien
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        // 1. Capa del Cuerpo (Forma de fantasma/corazón muy redondo)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val centerX = width / 2f

            val path = Path().apply {
                // Empezamos arriba al centro
                moveTo(centerX, height * 0.1f)

                // Curva superior izquierda (muy redonda)
                cubicTo(
                    x1 = centerX - width * 0.3f, y1 = height * 0.05f,
                    x2 = centerX - width * 0.5f, y2 = height * 0.2f,
                    x3 = centerX - width * 0.5f, y3 = height * 0.45f
                )

                // Curva inferior izquierda (redondeando hacia el centro abajo)
                cubicTo(
                    x1 = centerX - width * 0.5f, y1 = height * 0.7f,
                    x2 = centerX - width * 0.3f, y2 = height * 0.95f,
                    x3 = centerX, y3 = height * 0.98f // Punto más bajo, redondeado
                )

                // Curva inferior derecha (espejo)
                cubicTo(
                    x1 = centerX + width * 0.3f, y1 = height * 0.95f,
                    x2 = centerX + width * 0.5f, y2 = height * 0.7f,
                    x3 = centerX + width * 0.5f, y3 = height * 0.45f
                )

                // Curva superior derecha (espejo)
                cubicTo(
                    x1 = centerX + width * 0.5f, y1 = height * 0.2f,
                    x2 = centerX + width * 0.3f, y2 = height * 0.05f,
                    x3 = centerX, y3 = height * 0.1f
                )
                close()
            }
            drawPath(path = path, color = Color.White)
        }

        // Contenedor para los rasgos faciales para centrarlos juntos
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-5).dp), // Mover toda la cara un poco hacia arriba
            contentAlignment = Alignment.Center
        ) {
            // 2. Capa de los Ojos (Mucho más grandes y juntos)
            Row(
                modifier = Modifier.width(90.dp), // Ancho total que ocupan los ojos
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ojo Izquierdo
                EyeComponent()
                // Ojo Derecho
                EyeComponent()
            }

            // 3. Capa de las Mejillas (Debajo de los ojos)
            Row(
                modifier = Modifier
                    .width(100.dp) // Un poco más separadas que los ojos
                    .offset(y = 22.dp), // Posición vertical debajo de los ojos
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Mejilla izquierda
                Box(
                    modifier = Modifier
                        .size(20.dp) // Más grandes
                        .clip(CircleShape)
                        .background(PalePink) // Color sólido pálido
                )
                // Mejilla derecha
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(PalePink)
                )
            }

            // 4. Capa de la Sonrisa (Pequeña y sutil)
            Canvas(
                modifier = Modifier
                    .width(24.dp) // Estrecha
                    .height(12.dp)
                    .offset(y = 32.dp) // Más abajo
            ) {
                drawArc(
                    color = GreySmile,
                    startAngle = 20f, // Ángulo inicial para hacerla más plana
                    sweepAngle = 140f, // Ángulo de barrido más corto
                    useCenter = false,
                    topLeft = Offset.Zero,
                    size = this.size,
                    style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }
}

// Componente reutilizable para el ojo
@Composable
fun EyeComponent() {
    Box(
        modifier = Modifier
            .size(36.dp) // Ojos mucho más grandes
            .clip(CircleShape)
            .background(Color(0xFF212121)) // Negro casi puro
    ) {
        // Pupila blanca
        Box(
            modifier = Modifier
                .size(14.dp) // Pupila más grande
                // Posicionada arriba y a la izquierda dentro del ojo
                .align(Alignment.TopStart)
                .offset(x = 5.dp, y = 5.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginAvatarExact() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground), // Usando tu color de fondo oscuro
        contentAlignment = Alignment.Center
    ) {
        LoginAvatar()
    }
}