package com.example.saltasalta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.ui.Cards.LoginCard
import com.example.saltasalta.ui.theme.DarkBackground
import com.example.saltasalta.ui.theme.SaltaSaltaTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {}
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
            verticalArrangement = Arrangement.spacedBy(95.dp)
        ) {
            // Título "Bienvenido"
            Text(
                text = "Bienvenido",
                color = Color.White,
                fontSize = 32.sp,

                )

            // Card de login con avatar
            LoginCard(
                modifier = Modifier.fillMaxWidth(),
                onLoginClick = onLoginClick,
                onRegisterClick = onRegisterClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    SaltaSaltaTheme {
        LoginScreen(
            onLoginClick = { username, password ->
                // Preview action
            },
            onRegisterClick = {
                // Preview action
            }
        )
    }
}