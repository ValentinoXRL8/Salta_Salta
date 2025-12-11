package com.example.saltasalta.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.saltasalta.ui.game.GameView
import com.example.saltasalta.viewmodel.GameViewModel

@Composable
fun GamePlayScreen(
    viewModel: GameViewModel,
    onBackToMenu: () -> Unit
) {
    val context = LocalContext.current
    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    val gyro = remember { sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) }

    var showGameOver by remember { mutableStateOf(false) }
    var lastScore by remember { mutableStateOf(0) }

    Box(Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                GameView(ctx).apply {
                    onGameOver = { score ->
                        lastScore = score
                        showGameOver = true
                        viewModel.sendScore(score)
                    }
                    viewModel.gameViewRef = this
                    startGame()
                }
            }
        )

        if (showGameOver) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                tonalElevation = 8.dp
            ) {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Game Over - score: $lastScore")
                    androidx.compose.foundation.layout.Spacer(Modifier.padding(6.dp))
                    Button(onClick = {
                        showGameOver = false
                        viewModel.restartGame()
                    }) { Text("Reintentar") }
                    androidx.compose.foundation.layout.Spacer(Modifier.padding(4.dp))
                    Button(onClick = onBackToMenu) { Text("Menú") }
                }
            }
        }
    }

    // Sensor listener (giroscopio)
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val tiltX = event.values.getOrNull(1) ?: 0f // eje lateral; ajusta si necesario
                viewModel.onTilt(tiltX)
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        if (gyro != null) {
            sensorManager.registerListener(
                listener, gyro, SensorManager.SENSOR_DELAY_GAME
            )
        }
        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }
}