package com.example.postvision.ui.screens.camerax

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
import kotlin.math.max

@Composable
fun OverlayCanvas(
    results: PoseLandmarkerResult?,
    imageHeight: Int,
    imageWidth: Int,
    runningMode: RunningMode,
    isFrontCamera: Boolean = false,
    showLandmarks: Boolean // Controla a visibilidade do esqueleto
) {
    Canvas(modifier = Modifier.fillMaxSize()) {

        // LANDMARKS: Só aparecem se "showLandmarks" for verdadeiro (gravando + corpo inteiro)
        if (showLandmarks && results != null) {
            val scaleX = size.width / imageWidth
            val scaleY = size.height / imageHeight
            val scaleFactor = max(scaleX, scaleY)
            val scaledImageWidth = imageWidth * scaleFactor
            val scaledImageHeight = imageHeight * scaleFactor
            val offsetX = (size.width - scaledImageWidth) / 2
            val offsetY = (size.height - scaledImageHeight) / 2

            for (landmarkList in results.landmarks()) {
                com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker.POSE_LANDMARKS.forEach {
                    val start = landmarkList[it!!.start()]
                    val end = landmarkList[it.end()]
                    val startX = transformX(start.x(), scaledImageWidth, offsetX, isFrontCamera)
                    val startY = start.y() * scaledImageHeight + offsetY
                    val endX = transformX(end.x(), scaledImageWidth, offsetX, isFrontCamera)
                    val endY = end.y() * scaledImageHeight + offsetY
                    drawLine(color = Color(0xFF9C27B0), start = Offset(startX, startY), end = Offset(endX, endY), strokeWidth = 8f)
                }
                for (lm in landmarkList) {
                    val x = transformX(lm.x(), scaledImageWidth, offsetX, isFrontCamera)
                    val y = lm.y() * scaledImageHeight + offsetY
                    drawCircle(color = Color.Yellow, radius = 10f, center = Offset(x, y))
                }
            }
        }
    }
}

private fun transformX(
    normalizedX: Float,
    scaledImageWidth: Float,
    offsetX: Float,
    isFrontCamera: Boolean
): Float {
    val x = normalizedX * scaledImageWidth + offsetX
    return if (isFrontCamera) {
        scaledImageWidth + offsetX - (x - offsetX)
    } else {
        x
    }
}