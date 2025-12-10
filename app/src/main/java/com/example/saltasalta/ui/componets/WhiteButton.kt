package com.example.saltasalta.ui.theme.componets

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WhiteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = Color.Gray,
            contentColor = Color.Black,
            disabledContentColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = label,
            color = Color.Black
        )
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000 // Fondo negro para ver el botón blanco
)
@Composable
fun WhiteButtonPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Botón habilitado
        WhiteButton(
            onClick = {},
            label = "Botón habilitado"
        )

        // Espacio entre botones
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(16.dp))

        // Botón deshabilitado (para ver cómo queda en gris)
        WhiteButton(
            onClick = {},
            enabled = false,
            label = "Botón deshabilitado"
        )
    }
}

