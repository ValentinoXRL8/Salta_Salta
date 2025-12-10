package com.example.saltasalta.ui.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Choreographer
import android.view.View
import kotlin.math.max
import kotlin.random.Random

data class Platform(
    var x: Float,
    var y: Float,
    var w: Float,
    var h: Float = 18f
)

class GameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs), Choreographer.FrameCallback {

    // Física y configuración
    private val gravity = 2200f          // px/s^2
    private val bounceVy = -1400f        // rebote al colisionar
    private val maxVy = 2500f            // límite velocidad

    // Personaje
    private var blobX = 0f
    private var blobY = 0f
    private var blobVy = 0f
    private val blobSize = 110f

    // Input lateral (por giroscopio)
    private var inputTilt = 0f
    private val tiltFactor = 750f        // factor de sensibilidad lateral

    // Plataformas
    private val platforms = mutableListOf<Platform>()
    private val platformCount = 8
    private val platformMinW = 140f
    private val platformMaxW = 200f
    private val platformSpacing = 0.18f  // 18% de la altura de pantalla entre plataformas

    // Pinturas
    private val paintBg = Paint().apply { color = Color.parseColor("#0F0F0F") }
    private val paintPlatform = Paint().apply { color = Color.parseColor("#3A3A3A") }
    private val paintBlob = Paint().apply { color = Color.WHITE }
    private val paintCheek = Paint().apply { color = Color.parseColor("#FFB6C1") }
    private val paintEye = Paint().apply { color = Color.BLACK }
    private val paintScore = Paint().apply {
        color = Color.WHITE
        textSize = 42f
        isAntiAlias = true
    }

    // Scroll / score
    private var scrollOffset = 0f
    private var score = 0f
    var onGameOver: ((Int) -> Unit)? = null

    private var lastFrameNanos: Long = 0
    private var running = false

    fun startGame() {
        resetWorld()
        running = true
        lastFrameNanos = 0
        Choreographer.getInstance().postFrameCallback(this)
    }

    fun stopGame() {
        running = false
        Choreographer.getInstance().removeFrameCallback(this)
    }

    fun setTilt(value: Float) {
        inputTilt = value
    }

    private fun resetWorld() {
        if (width == 0 || height == 0) return
        blobX = width / 2f
        blobY = height * 0.7f
        blobVy = 0f
        scrollOffset = 0f
        score = 0f
        platforms.clear()
        val spacing = height / platformCount.toFloat()
        repeat(platformCount) { i ->
            val w = Random.nextFloat() * (platformMaxW - platformMinW) + platformMinW
            val x = Random.nextFloat() * (width - w)
            val y = height - i * spacing
            platforms.add(Platform(x, y, w))
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        resetWorld()
    }

    override fun doFrame(frameTimeNanos: Long) {
        if (!running) return
        if (lastFrameNanos == 0L) lastFrameNanos = frameTimeNanos
        val dt = (frameTimeNanos - lastFrameNanos) / 1_000_000_000f
        lastFrameNanos = frameTimeNanos

        update(dt)
        invalidate()

        Choreographer.getInstance().postFrameCallback(this)
    }

    private fun update(dt: Float) {
        if (dt <= 0f) return

        // Movimiento lateral por tilt
        val vx = inputTilt * tiltFactor
        blobX += vx * dt

        // Pac-Man en X
        if (blobX < -blobSize) blobX = width + blobSize
        if (blobX > width + blobSize) blobX = -blobSize

        // Física vertical
        blobVy += gravity * dt
        blobVy = blobVy.coerceIn(-maxVy, maxVy)
        blobY += blobVy * dt

        // Colisión con plataformas (solo si va cayendo)
        if (blobVy > 0) {
            val feetY = blobY + blobSize * 0.5f
            platforms.forEach { p ->
                val isOver = blobX + blobSize * 0.5f > p.x &&
                        blobX - blobSize * 0.5f < p.x + p.w
                val isTouching = feetY in p.y..(p.y + p.h)
                if (isOver && isTouching) {
                    blobVy = bounceVy
                    blobY = p.y - blobSize * 0.5f
                }
            }
        }

        // Scroll infinito: si sube de la mitad, bajamos el mundo
        val mid = height * 0.5f
        if (blobY < mid) {
            val diff = mid - blobY
            blobY = mid
            platforms.forEach { it.y += diff }
            scrollOffset += diff
            score = max(score, scrollOffset)
        }

        // Reciclado de plataformas (reaparecen arriba)
        platforms.forEach { p ->
            if (p.y > height + 40f) {
                val w = Random.nextFloat() * (platformMaxW - platformMinW) + platformMinW
                p.w = w
                p.x = Random.nextFloat() * (width - w)

                // Encontrar la plataforma más alta (menor Y) que esté visible
                val highestPlatform = platforms
                    .filter { it.y >= 0 && it.y <= height }
                    .minByOrNull { it.y }

                // Colocar la nueva plataforma a una distancia fija arriba de la más alta
                val spacing = height * platformSpacing
                p.y = (highestPlatform?.y ?: height * 0.5f) - spacing

                // Asegurar que no quede fuera de la pantalla por arriba
                p.y = p.y.coerceAtLeast(height * 0.05f)
            }
        }

        // Game Over
        if (blobY - blobSize > height + 80f) {
            running = false
            onGameOver?.invoke(score.toInt())
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Fondo
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBg)

        // Plataformas
        platforms.forEach { p ->
            canvas.drawRoundRect(
                RectF(p.x, p.y, p.x + p.w, p.y + p.h),
                6f, 6f, paintPlatform
            )
        }

        // Blob
        canvas.drawCircle(blobX, blobY, blobSize * 0.5f, paintBlob)
        // Ojos
        val eyeOffsetX = blobSize * 0.16f
        val eyeOffsetY = blobSize * -0.05f
        val eyeR = blobSize * 0.14f
        canvas.drawCircle(blobX - eyeOffsetX, blobY + eyeOffsetY, eyeR, paintEye)
        canvas.drawCircle(blobX + eyeOffsetX, blobY + eyeOffsetY, eyeR, paintEye)
        // Mejillas
        val cheekR = blobSize * 0.1f
        val cheekOffsetX = blobSize * 0.24f
        val cheekOffsetY = blobSize * 0.2f
        canvas.drawCircle(blobX - cheekOffsetX, blobY + cheekOffsetY, cheekR, paintCheek)
        canvas.drawCircle(blobX + cheekOffsetX, blobY + cheekOffsetY, cheekR, paintCheek)

        // Score
        canvas.drawText("score: ${score.toInt()}", 24f, 64f, paintScore)
    }
}

