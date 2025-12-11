package com.example.saltasalta.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saltasalta.data.models.UserResponse
import com.example.saltasalta.ui.screens.EditProfileScreen
import com.example.saltasalta.ui.screens.GameMenuScreen
import com.example.saltasalta.ui.screens.GamePlayScreen
import com.example.saltasalta.ui.screens.LoginScreen
import com.example.saltasalta.ui.screens.RegisterScreen
import com.example.saltasalta.ui.screens.TopPlayersScreen
import com.example.saltasalta.viewmodel.GameViewModel
import com.example.saltasalta.viewmodel.TopPlayersViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    currentUser: MutableState<UserResponse?>,
    onAuth: (UserResponse) -> Unit,
    onLogin: (String, String) -> Unit,
    onRegister: (String, String, String) -> Unit,
    onSaveProfile: (String, String, String) -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit,
    gameViewModel: GameViewModel,
    onBackToMenu: () -> Unit,
    topPlayersViewModel: TopPlayersViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Pantalla de Login
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { username, password ->
                    onLogin(username, password)
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // Pantalla de Registro
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = { username, password, confirm ->
                    onRegister(username, password, confirm)
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // Menú del juego
        composable(Screen.Menu.route) {
            val user = currentUser.value
            val bestScore = if (user != null) {
                gameViewModel.getBestScore(user.id)
            } else {
                0
            }

            GameMenuScreen(
                onPlayClick = {
                    navController.navigate(Screen.Game.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },
                onTopClick = {
                    navController.navigate(Screen.TopPlayers.route)
                },
                bestScore = bestScore
            )
        }

        // Pantalla del juego
        composable(Screen.Game.route) {
            GamePlayScreen(
                viewModel = gameViewModel,
                onBackToMenu = onBackToMenu
            )
        }

        // Pantalla de perfil
        composable(Screen.Profile.route) {
            EditProfileScreen(
                currentUser = currentUser.value,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = { username, oldPass, newPass ->
                    onSaveProfile(username, oldPass, newPass)
                },
                onLogoutClick = onLogout,
                onDeleteClick = onDeleteAccount
            )
        }

        // Pantalla de top jugadores
        composable(Screen.TopPlayers.route) {
            TopPlayersScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}


