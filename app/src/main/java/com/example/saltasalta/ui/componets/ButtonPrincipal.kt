package com.example.saltasalta.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.String

@Composable
fun button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String
){
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,


        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(11.dp)

    ){
        Text(
            text = label,
            color = Color.Black
        )
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewButton() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1b1d23)),
        contentAlignment = Alignment.Center
    ) {
        button(onClick = {}, modifier = Modifier, enabled = true, label = "Iniciar sesion")
    }

}
