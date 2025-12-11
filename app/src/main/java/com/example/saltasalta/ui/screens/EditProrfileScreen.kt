package com.example.saltasalta.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.data.models.UserResponse
import com.example.saltasalta.ui.componets.LoginAvatar
import com.example.saltasalta.ui.componets.PasswordField
import com.example.saltasalta.ui.componets.UserInputField
import com.example.saltasalta.ui.theme.componets.WhiteButton

@Composable
fun EditProfileScreen(
    currentUser: UserResponse? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (String, String, String) -> Unit = { _, _, _ -> },
    onLogoutClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
){
    var user by remember { mutableStateOf(currentUser?.username ?: "Usuario") }
    var password by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F))
    ) {
        // Botón regresar
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

        // Tarjeta central
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(top = 96.dp)
                .align(Alignment.TopCenter),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF111111)),
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Editar perfil",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Usuario
                LabeledField(label = "Usuario") {
                    UserInputField(
                        value = user,
                        onValueChange = { user = it },
                        label = "Usuario"
                    )
                }

                // Contraseña
                LabeledField(label = "Contraseña") {
                    PasswordField(
                        value = password,
                        onValueChange = { password = it },
                        label = "********"
                    )
                }

                // Cambiar contraseña
                LabeledField(label = "Cambiar contraseña") {
                    PasswordField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = "Nueva contraseña"
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                WhiteButton(
                    onClick = { onSaveClick(user, password, newPassword) },
                    modifier = Modifier.fillMaxWidth(),
                    label = "Guardar"
                )

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Cerrar sesión",
                        color = Color(0xFFCCCCCC),
                        modifier = Modifier
                            .clickable { onLogoutClick() }
                            .padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Eliminar cuenta",
                        color = Color(0xFFFF8A80),
                        modifier = Modifier
                            .clickable { onDeleteClick() }
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }

        // Montañas inferiores
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .align(Alignment.BottomCenter)
        ) {
            val terrainColor = Color(0xFF1A1A1A)
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width * 0.15f, size.height * 0.55f)
                lineTo(size.width * 0.35f, size.height * 0.85f)
                lineTo(size.width * 0.6f, size.height * 0.6f)
                lineTo(size.width * 0.85f, size.height * 0.9f)
                lineTo(size.width, size.height * 0.65f)
                lineTo(size.width, size.height)
                close()
            }
            drawPath(path, terrainColor)
        }

        // Mascota en esquina inferior derecha
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 8.dp)
                .size(110.dp)
        ) {
            LoginAvatar(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LabeledField(label: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(text = label, color = Color(0xFFCCCCCC), fontSize = 14.sp)
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000, device = "spec:width=411dp,height=891dp")
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}