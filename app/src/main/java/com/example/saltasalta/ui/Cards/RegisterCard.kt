package com.example.saltasalta.ui.Cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import com.example.saltasalta.ui.componets.LoginAvatar
import com.example.saltasalta.ui.componets.PasswordField
import com.example.saltasalta.ui.componets.UserInputField
import com.example.saltasalta.ui.componets.button
import com.example.saltasalta.ui.theme.CardBackground
import com.example.saltasalta.ui.theme.DarkBackground

@Composable
fun RegisterCard(
    modifier: Modifier = Modifier,
    onRegisterClick: (String, String, String) -> Unit = { _, _, _ -> }
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Estados de error
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    // Función de validación
    fun validateFields(): Boolean {
        var isValid = true

        // Validar usuario
        if (username.isBlank()) {
            usernameError = "El usuario es requerido"
            isValid = false
        } else if (username.length < 3) {
            usernameError = "El usuario debe tener al menos 3 caracteres"
            isValid = false
        } else {
            usernameError = ""
        }

        // Validar contraseña
        if (password.isBlank()) {
            passwordError = "La contraseña es requerida"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        } else {
            passwordError = ""
        }

        // Validar confirmar contraseña
        if (confirmPassword.isBlank()) {
            confirmPasswordError = "Confirma tu contraseña"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordError = "Las contraseñas no coinciden"
            isValid = false
        } else {
            confirmPasswordError = ""
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
                            // Limpiar error de confirmación si cambia la contraseña
                            if (confirmPasswordError.isNotEmpty() && confirmPassword.isNotEmpty()) {
                                confirmPasswordError = ""
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

                // Campo Confirmar Contraseña
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Confirmar contraseña",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    PasswordField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            if (confirmPasswordError.isNotEmpty()) {
                                confirmPasswordError = ""
                            }
                        },
                        label = "Confirmar contraseña"
                    )
                    if (confirmPasswordError.isNotEmpty()) {
                        Text(
                            text = confirmPasswordError,
                            color = Color(0xFFFF6B6B),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                // Botón CREAR CUENTA
                button(
                    onClick = {
                        if (validateFields()) {
                            onRegisterClick(username, password, confirmPassword)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    label = "CREAR CUENTA"
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        RegisterCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onRegisterClick = { user, pass, confirmPass ->
                // Preview action
            }
        )
    }
}