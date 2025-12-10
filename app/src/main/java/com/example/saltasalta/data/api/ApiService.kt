package com.example.saltasalta.data.api

import com.example.saltasalta.data.models.ApiResponse
import com.example.saltasalta.data.models.ScoreRequest
import com.example.saltasalta.data.models.UpdateUserRequest
import com.example.saltasalta.data.models.UserRequest
import com.example.saltasalta.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @POST("api/usuarios/registro")
    suspend fun registrarUsuario(@Body user: UserRequest): Response<ApiResponse<UserResponse>>

    @POST("api/usuarios/login")
    suspend fun loginUsuario(@Body credentials: UserRequest): Response<ApiResponse<UserResponse>>

    @PUT("api/usuarios/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: Int,
        @Body body: UpdateUserRequest
    ): Response<ApiResponse<UserResponse>>

    @DELETE("api/usuarios/{id}")
    suspend fun eliminarUsuario(
        @Path("id") id: Int
    ): Response<ApiResponse<Unit>>

    @POST("api/puntuaciones")
    suspend fun guardarPuntuacion(
        @Body body: ScoreRequest
    ): Response<ApiResponse<Unit>>

    @GET("api/usuarios")
    suspend fun obtenerUsuarios(): Response<List<UserResponse>>
}

