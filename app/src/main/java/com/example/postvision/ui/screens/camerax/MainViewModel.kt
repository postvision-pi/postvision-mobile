package com.example.postvision.ui.screens.camerax

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mediapipe.tasks.vision.core.RunningMode
import androidx.camera.core.CameraSelector
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import kotlin.math.abs
import kotlin.math.atan2
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var currentModel by mutableIntStateOf(PoseLandmarkerHelper.MODEL_POSE_LANDMARKER_LITE)
        private set
    var currentDelegate by mutableIntStateOf(PoseLandmarkerHelper.DELEGATE_GPU)
        private set
    var currentMinPoseDetectionConfidence by mutableFloatStateOf(PoseLandmarkerHelper.DEFAULT_POSE_DETECTION_CONFIDENCE)
        private set
    var currentMinPoseTrackingConfidence by mutableFloatStateOf(PoseLandmarkerHelper.DEFAULT_POSE_TRACKING_CONFIDENCE)
        private set
    var currentMinPosePresenceConfidence by mutableFloatStateOf(PoseLandmarkerHelper.DEFAULT_POSE_PRESENCE_CONFIDENCE)
        private set

    var cameraSelector: CameraSelector by mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA)
        private set

    fun switchCamera() {
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
            CameraSelector.DEFAULT_BACK_CAMERA
        } else {
            CameraSelector.DEFAULT_FRONT_CAMERA
        }
    }

    fun setModel(model: Int) { currentModel = model }
    fun setDelegate(delegate: Int) { currentDelegate = delegate }
    fun setMinPoseDetectionConfidence(confidence: Float) { currentMinPoseDetectionConfidence = confidence.coerceIn(0.1f, 0.9f) }
    fun setMinPoseTrackingConfidence(confidence: Float) { currentMinPoseTrackingConfidence = confidence.coerceIn(0.1f, 0.9f) }
    fun setMinPosePresenceConfidence(confidence: Float) { currentMinPosePresenceConfidence = confidence.coerceIn(0.1f, 0.9f) }

    fun createPoseLandmarkerHelper(runningMode: RunningMode, context: Context): PoseLandmarkerHelper {
        return PoseLandmarkerHelper(
            context = context,
            runningMode = runningMode,
            minPoseDetectionConfidence = currentMinPoseDetectionConfidence,
            minPoseTrackingConfidence = currentMinPoseTrackingConfidence,
            minPosePresenceConfidence = currentMinPosePresenceConfidence,
            currentDelegate = currentDelegate,
            currentModel = currentModel
        )
    }

    var squatCount by mutableIntStateOf(0)
        private set

    var incorrectSquats by mutableIntStateOf(0)
        private set
    var sessionDurationSeconds by mutableIntStateOf(0)
        private set
    private var sessionStartTime: Long = 0
    private var currentSquatHasError = false

    var isRecording by mutableStateOf(false)
    var countDownTime by mutableStateOf(0)
    var exerciceStage by mutableStateOf(0)
    var hasError by mutableStateOf(false)
    var errorLocation by mutableStateOf<Offset?>(null)

    // NOVO: Verifica se o corpo inteiro está na câmara
    var isFullyFramed by mutableStateOf(false)
        private set

    fun startAnalysis(){
        viewModelScope.launch {
            squatCount = 0
            incorrectSquats = 0
            sessionDurationSeconds = 0
            hasError = false
            currentSquatHasError = false
            exerciceStage = 0
            countDownTime = 3 // 3 Segundos para começar

            while (countDownTime > 0){
                delay(1000)
                countDownTime--
            }
            isRecording = true
            sessionStartTime = System.currentTimeMillis()
        }
    }

    fun stopAnalysis(onNavigateToSummary: () -> Unit) {
        isRecording = false
        if (sessionStartTime > 0) {
            sessionDurationSeconds = ((System.currentTimeMillis() - sessionStartTime) / 1000).toInt()
        }
        onNavigateToSummary()
    }

    fun processPose(landmarks: List<NormalizedLandmark>) {
        try {
            // 1. VERIFICA ENQUADRAMENTO (Testa se o Nariz e os Tornozelos estão dentro do vídeo)
            if (landmarks.size > 28) {
                val nose = landmarks[0]
                val leftAnkle = landmarks[27]
                val rightAnkle = landmarks[28]
                isFullyFramed = (nose.y() in 0.0f..1.0f) && (leftAnkle.y() in 0.0f..1.0f) && (rightAnkle.y() in 0.0f..1.0f)
            } else {
                isFullyFramed = false
            }

            // Só analisa o agachamento se estiver a gravar
            if (!isRecording) return

            val leftKneeAngle = calculateAngle(landmarks[23], landmarks[25], landmarks[27])
            val rightKneeAngle = calculateAngle(landmarks[24], landmarks[26], landmarks[28])
            val avgKnee = (leftKneeAngle + rightKneeAngle) / 2

            val hipWidth = abs(landmarks[23].x() - landmarks[24].x())
            val kneeWidth = abs(landmarks[25].x() - landmarks[26].x())

            // 2. VERIFICAÇÃO DE ERRO NOS JOELHOS
            val isKneesTooClose = kneeWidth < hipWidth * 0.85

            if (isKneesTooClose) {
                hasError = true
                errorLocation = Offset(landmarks[25].x(), landmarks[25].y())

                // NOVO: "Pane" imediata! Se os joelhos estão fechados e a pessoa tenta descer (ângulo menor que 160), condena a repetição.
                if (avgKnee < 160) {
                    currentSquatHasError = true
                }
            } else {
                hasError = false
            }

            // 3. CONTROLE DOS ESTÁGIOS
            when (exerciceStage) {
                0 -> {
                    if (avgKnee < 130) {
                        exerciceStage = 1
                        currentSquatHasError = isKneesTooClose
                    }
                }
                1 -> {
                    if (isKneesTooClose) {
                        currentSquatHasError = true
                    }
                    if (avgKnee < 85) {
                        exerciceStage = 2
                    } else if (avgKnee > 160) {
                        incorrectSquats++
                        exerciceStage = 0
                        currentSquatHasError = false
                    }
                }
                2 -> {
                    if (isKneesTooClose) {
                        currentSquatHasError = true
                    }
                    if (avgKnee > 160) {
                        if (currentSquatHasError) {
                            incorrectSquats++
                        } else {
                            squatCount++
                        }
                        exerciceStage = 0
                        currentSquatHasError = false
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("PI_MONITOR", "Erro na análise: ${e.message}")
        }
    }

    private fun calculateAngle(
        p1: NormalizedLandmark,
        p2: NormalizedLandmark,
        p3: NormalizedLandmark
    ): Double {
        return try {
            val radians = atan2((p3.y() - p2.y()).toDouble(), (p3.x() - p2.x()).toDouble()) -
                    atan2((p1.y() - p2.y()).toDouble(), (p1.x() - p2.x()).toDouble())
            var angle = abs(radians * 180.0 / Math.PI)
            if (angle > 180.0) angle = 360.0 - angle
            angle
        } catch (e: Exception) {
            Log.e("SquatDetection", "Erro no cálculo do ângulo: ${e.message}")
            0.0
        }
    }
}