package com.example.saltasalta.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Menu : Screen("menu")
    object Game : Screen("game")
    object Profile : Screen("profile")
    object TopPlayers : Screen("top_players")
}


