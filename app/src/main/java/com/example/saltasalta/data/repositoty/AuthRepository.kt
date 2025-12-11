package com.example.saltasalta.data.repositoty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.saltasalta.data.api.RetrofitClient
import com.example.saltasalta.data.repository.AuthRepository
import com.example.saltasalta.data.repository.AuthResult
import com.example.saltasalta.navigation.Screen
import com.example.saltasalta.ui.theme.SaltaSaltaTheme
import com.example.saltasalta.viewmodel.GameViewModel
import com.example.saltasalta.viewmodel.TopPlayersViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaltaSaltaTheme {
                AppNavigation(authRepository = authRepository)
            }
        }
    }
}

@Composable
fun AppNavigation(authRepository: AuthRepository) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }
    val currentUserState = remember { mutableStateOf<UserResponse?>(null) }

    // GameViewModel para enviar score; usa RetrofitClient.apiService
    val gameViewModel = remember {
        GameViewModel(
            api = RetrofitClient.apiService,
            userIdProvider = { currentUserState.value?.id ?: -1 },
            context = context
        )
    }

    // ViewModel para listar usuarios en TopPlayers
    val topPlayersViewModel = remember {
        TopPlayersViewModel(RetrofitClient.apiService)
    }

    fun showMessage(message: String) {
        coroutineScope.launch { snackbarHostState.showSnackbar(message) }
    }

    fun navigateToMenu() {
        navController.navigate(Screen.Menu.route) {
            popUpTo(Screen.Login.route) { inclusive = true }
        }
    }

    fun handleLogin(username: String, password: String) {
        isLoading = true
        coroutineScope.launch {
            when (val result = authRepository.login(username, password)) {
                is AuthResult.Success -> {
                    currentUserState.value = result.user
                    showMessage("Login exitoso")
                    navigateToMenu()
                }
                is AuthResult.Error -> showMessage(result.message)
            }
            isLoading = false
        }
    }

    fun handleRegister(username: String, password: String, confirm: String) {
        if (password != confirm) {
            showMessage("Las contraseñas no coinciden")
            return
        }
        isLoading = true
        coroutineScope.launch {
            when (val result = authRepository.register(username, password)) {
                is AuthResult.Success -> {
                    currentUserState.value = result.user
                    showMessage("Usuario registrado exitosamente")
                    navigateToMenu()
                }
                is AuthResult.Error -> showMessage(result.message)
            }
            isLoading = false
        }
    }

    fun handleSaveProfile(username: String, oldPass: String, newPass: String) {
        val user = currentUserState.value ?: return
        isLoading = true
        coroutineScope.launch {
            val result = authRepository.updateUser(
                id = user.id,
                username = username,
                password = if (newPass.isNotBlank()) newPass else null
            )
            when (result) {
                is AuthResult.Success -> {
                    currentUserState.value = result.user
                    showMessage("Perfil actualizado")
                }
                is AuthResult.Error -> showMessage(result.message)
            }
            isLoading = false
        }
    }

    fun handleDeleteAccount() {
        val user = currentUserState.value ?: return
        isLoading = true
        coroutineScope.launch {
            val result = authRepository.deleteUser(user.id)
            when (result) {
                is AuthResult.Success -> {
                    currentUserState.value = null
                    showMessage("Cuenta eliminada")
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
                is AuthResult.Error -> showMessage(result.message)
            }
            isLoading = false
        }
    }

    fun handleLogout() {
        currentUserState.value = null
        navController.navigate(Screen.Login.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    NavGraph(
        navController = navController,
        currentUser = currentUserState,
        onAuth = { currentUserState.value = it },
        onLogin = ::handleLogin,
        onRegister = ::handleRegister,
        onSaveProfile = ::handleSaveProfile,
        onLogout = ::handleLogout,
        onDeleteAccount = ::handleDeleteAccount,
        gameViewModel = gameViewModel,
        onBackToMenu = {
            navController.navigate(Screen.Menu.route) {
                popUpTo(Screen.Menu.route) { inclusive = true }
            }
        },
        topPlayersViewModel = topPlayersViewModel
    )

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.fillMaxSize()
    )
}