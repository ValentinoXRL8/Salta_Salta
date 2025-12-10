package com.example.saltasalta.data.models

data class ApiResponse<T>(
    val mensaje: String? = null,
    val error: String? = null,
    val usuario: T? = null,
    val detalles: String? = null
)

