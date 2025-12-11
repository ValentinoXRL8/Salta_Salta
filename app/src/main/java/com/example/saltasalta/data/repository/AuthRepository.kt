package com.example.saltasalta.data.repository

import com.example.saltasalta.data.api.RetrofitClient
import com.example.saltasalta.data.models.UpdateUserRequest
import com.example.saltasalta.data.models.UserRequest
import com.example.saltasalta.data.models.UserResponse

sealed class AuthResult {
    data class Success(val user: UserResponse) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun login(username: String, password: String): AuthResult {
        return try {
            val request = UserRequest(username, password)
            val response = apiService.loginUsuario(request)
            
            if (response.isSuccessful) {
                val body = response.body()
                val user = body?.usuario
                
                if (user != null) {
                    AuthResult.Success(user)
                } else {
                    AuthResult.Error(body?.error ?: "Error desconocido")
                }
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }

    suspend fun register(username: String, password: String): AuthResult {
        return try {
            val request = UserRequest(username, password)
            val response = apiService.registrarUsuario(request)
            
            if (response.isSuccessful) {
                val body = response.body()
                val user = body?.usuario
                
                if (user != null) {
                    AuthResult.Success(user)
                } else {
                    AuthResult.Error(body?.error ?: "Error en el registro")
                }
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }

    suspend fun updateUser(id: Int, username: String, password: String?): AuthResult {
        return try {
            val request = UpdateUserRequest(username, password)
            val response = apiService.actualizarUsuario(id, request)
            
            if (response.isSuccessful) {
                val body = response.body()
                val user = body?.usuario
                
                if (user != null) {
                    AuthResult.Success(user)
                } else {
                    AuthResult.Error(body?.error ?: "Error al actualizar")
                }
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }

    suspend fun deleteUser(id: Int): AuthResult {
        return try {
            val response = apiService.eliminarUsuario(id)
            
            if (response.isSuccessful) {
                // Retornamos un usuario ficticio ya que la cuenta fue eliminada
                AuthResult.Success(UserResponse(id, "", ""))
            } else {
                AuthResult.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }
}


