package com.example.postvision.ui.screens.camerax

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mediapipe.tasks.vision.core.RunningMode
import androidx.camera.core.CameraSelector // IMPORT NECESSÁRIO
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import kotlin.math.abs
import kotlin.math.atan2
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar la lógica de la aplicación, incluyendo la configuración del PoseLandmarker
 * y el estado de la pantalla de galería.
 */
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

    // NOVO: Estado para armazenar o CameraSelector atual (inicia com câmera frontal)
    var cameraSelector: CameraSelector by mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA)
        private set

    // NOVO: Função para alternar a câmera
    fun switchCamera() {
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
            CameraSelector.DEFAULT_BACK_CAMERA
        } else {
            CameraSelector.DEFAULT_FRONT_CAMERA
        }
    }

    // Funções de configuração dos parâmetros do Pose Landmarker
    fun setModel(model: Int) {
        currentModel = model
    }

    fun setDelegate(delegate: Int) {
        currentDelegate = delegate
    }

    fun setMinPoseDetectionConfidence(confidence: Float) {
        currentMinPoseDetectionConfidence = confidence.coerceIn(0.1f, 0.9f)
    }

    fun setMinPoseTrackingConfidence(confidence: Float) {
        currentMinPoseTrackingConfidence = confidence.coerceIn(0.1f, 0.9f)
    }

    fun setMinPosePresenceConfidence(confidence: Float) {
        currentMinPosePresenceConfidence = confidence.coerceIn(0.1f, 0.9f)
    }

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

    // FUNCIONALIDADE DE IDENTIFCAR
    var squatCount by mutableIntStateOf(0)
        private set
    private var isSquatting = false // Trava para não contar múltiplas vezes no mesmo movimento

    var isRecording by mutableStateOf(false)
    var countDownTime by mutableStateOf(0)

    var exerciceStage by mutableStateOf(0)

    var hasError by mutableStateOf(false)
    var errorLocation by mutableStateOf<Offset?>(null)

    fun startAnalysis(){
        viewModelScope.launch {
            countDownTime = 10
            while (countDownTime > 0){
                delay(1000)
                countDownTime --
            }
            isRecording = true
        }
    }
    // Faltou continuar o process
    fun processPose(landmarks: List<NormalizedLandmark>) {
        // Log para confirmar que a função está sendo executada
        Log.d("PI_MONITOR", "--- Nova captura recebida ---")

        if (landmarks.size < 29) {
            Log.e("PI_MONITOR", "Erro: Pontos insuficientes (precisamos até o 28).")
            return
        }

        try {
            // Ponto 24: Quadril, 26: Joelho, 28: Tornozelo
            val angle = calculateAngle(landmarks[24], landmarks[26], landmarks[28])

            // Monitoramento em tempo real no Logcat
            Log.i("PI_MONITOR", "ANGULO: ${angle.toInt()} | STATUS: ${if(isSquatting) "Agachado" else "Em pé"} | TOTAL: $squatCount")

            if (angle < 105.0) {
                if (!isSquatting) Log.v("PI_MONITOR", "Evento: Detectou descida")
                isSquatting = true
            } else if (isSquatting && angle > 160.0) {
                squatCount++
                isSquatting = false
                Log.v("PI_MONITOR", "Evento: Repetição completa!")
            }
        } catch (e: Exception) {
            Log.e("PI_MONITOR", "Falha ao processar movimento: ${e.message}")
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