package com.example.saltasalta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.ui.Cards.RegisterCard
import com.example.saltasalta.ui.theme.DarkBackground
import com.example.saltasalta.ui.theme.SaltaSaltaTheme
import com.example.saltasalta.ui.theme.TextSecondary

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: (String, String, String) -> Unit = { _, _, _ -> },
    onBackToLogin: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Botón para volver a login (arriba a la izquierda)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(
                    onClick = onBackToLogin
                ) {
                    Text(
                        text = "← Volver",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }
            }

            // Título "Crear cuenta"
            Text(
                text = "Crear cuenta",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            // Card de registro con avatar
            RegisterCard(
                modifier = Modifier.fillMaxWidth(),
                onRegisterClick = onRegisterClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    SaltaSaltaTheme {
        RegisterScreen(
            onRegisterClick = { username, password, confirmPassword ->
                // Preview action
            }
        )
    }
}