package com.example.saltasalta.ui.Cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.saltasalta.ui.theme.CardBackground
import com.example.saltasalta.ui.theme.DarkBackground
import com.example.saltasalta.ui.theme.TextSecondary
import com.example.saltasalta.ui.componets.LoginAvatar
import com.example.saltasalta.ui.componets.PasswordField
import com.example.saltasalta.ui.componets.UserInputField
import com.example.saltasalta.ui.componets.button

@Composable
fun LoginCard(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estados de error
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Función de validación
    fun validateFields(): Boolean {
        var isValid = true

        // Validar usuario
        if (username.isBlank()) {
            usernameError = "El usuario es requerido"
            isValid = false
        } else {
            usernameError = ""
        }

        // Validar contraseña
        if (password.isBlank()) {
            passwordError = "La contraseña es requerida"
            isValid = false
        } else {
            passwordError = ""
        }

        return isValid
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar con offset negativo para que se superponga
        LoginAvatar(
            modifier = Modifier
                .offset(y = (-35).dp)
                .zIndex(1f)
        )

        // Card principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardBackground
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Espacio para el avatar
                Spacer(modifier = Modifier.height(20.dp))

                // Campo Usuario
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Usuario",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    UserInputField(
                        value = username,
                        onValueChange = {
                            username = it
                            if (usernameError.isNotEmpty()) {
                                usernameError = ""
                            }
                        },
                        label = "Usuario"
                    )
                    if (usernameError.isNotEmpty()) {
                        Text(
                            text = usernameError,
                            color = Color(0xFFFF6B6B),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                // Campo Contraseña
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Contraseña",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    PasswordField(
                        value = password,
                        onValueChange = {
                            password = it
                            if (passwordError.isNotEmpty()) {
                                passwordError = ""
                            }
                        },
                        label = "Contraseña"
                    )
                    if (passwordError.isNotEmpty()) {
                        Text(
                            text = passwordError,
                            color = Color(0xFFFF6B6B),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                // Botón INICIAR SESIÓN
                button(
                    onClick = {
                        if (validateFields()) {
                            onLoginClick(username, password)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    label = "INICIAR SESIÓN"
                )

                // Link Registrarse
                TextButton(
                    onClick = onRegisterClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Registrarse",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        LoginCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onLoginClick = { user, pass ->
                // Preview action
            },
            onRegisterClick = {
                // Preview action
            }
        )
    }
}