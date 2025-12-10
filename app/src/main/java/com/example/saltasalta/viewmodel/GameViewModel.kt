package com.example.saltasalta.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saltasalta.data.api.ApiService
import com.example.saltasalta.data.models.ScoreRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val api: ApiService,
    private val userIdProvider: () -> Int,
    private val context: Context? = null
) : ViewModel() {

    // Referencia opcional al GameView para restart directo
    var gameViewRef: com.example.saltasalta.ui.game.GameView? = null

    private val _tiltFlow = MutableSharedFlow<Float>(extraBufferCapacity = 1)
    val tiltFlow = _tiltFlow.asSharedFlow()

    private fun getSharedPreferences(): SharedPreferences? {
        return context?.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
    }

    fun onTilt(value: Float) {
        _tiltFlow.tryEmit(value)
        gameViewRef?.setTilt(value)
    }

    fun sendScore(score: Int) {
        viewModelScope.launch {
            try {
                val req = ScoreRequest(
                    usuario_id = userIdProvider(),
                    puntuacion = score
                )
                api.guardarPuntuacion(req)

                // Guardar mejor score localmente
                val userId = userIdProvider()
                if (userId > 0 && context != null) {
                    val prefs = getSharedPreferences()
                    val currentBest = prefs?.getInt("best_score_$userId", 0) ?: 0
                    if (score > currentBest) {
                        prefs?.edit()?.putInt("best_score_$userId", score)?.apply()
                    }
                }
            } catch (_: Exception) {
                // silenciar error de red; podrías loguearlo
            }
        }
    }

    fun getBestScore(userId: Int): Int {
        return if (userId > 0 && context != null) {
            getSharedPreferences()?.getInt("best_score_$userId", 0) ?: 0
        } else {
            0
        }
    }

    fun restartGame() {
        gameViewRef?.startGame()
    }
}

