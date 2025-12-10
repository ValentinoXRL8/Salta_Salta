package com.example.saltasalta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saltasalta.data.api.ApiService
import com.example.saltasalta.data.models.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UsersUiState(
    val loading: Boolean = false,
    val users: List<UserResponse> = emptyList(),
    val error: String? = null
)

class TopPlayersViewModel(
    private val api: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UsersUiState(loading = true)
            try {
                val resp = api.obtenerUsuarios()
                if (resp.isSuccessful) {
                    val list = resp.body() ?: emptyList()
                    _uiState.value = UsersUiState(loading = false, users = list)
                } else {
                    _uiState.value = UsersUiState(loading = false, error = resp.message())
                }
            } catch (e: Exception) {
                _uiState.value = UsersUiState(
                    loading = false,
                    error = e.message ?: "Error de red"
                )
            }
        }
    }
}