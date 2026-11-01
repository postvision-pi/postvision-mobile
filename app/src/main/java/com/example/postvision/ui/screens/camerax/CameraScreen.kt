package com.example.postvision.ui.screens.camerax

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.postvision.R
import com.example.postvision.ui.theme.Raleway
import com.google.mediapipe.tasks.vision.core.RunningMode
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class CameraScreen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CameraScreenContent(viewModel: MainViewModel, onFinishSession: () -> Unit = {}) {
        val context = LocalContext.current
        var hasPermission by remember {
            mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        }
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted -> hasPermission = isGranted }
        )

        LaunchedEffect(Unit) {
            if (!hasPermission) {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        var poseResults by remember { mutableStateOf<PoseLandmarkerHelper.ResultBundle?>(null) }
        val scaffoldState = rememberBottomSheetScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        if (hasPermission) {
            val poseLandmarkerHelper: PoseLandmarkerHelper = remember(
                viewModel.currentMinPoseDetectionConfidence,
                viewModel.currentMinPoseTrackingConfidence,
                viewModel.currentMinPosePresenceConfidence,
                viewModel.currentModel,
                viewModel.currentDelegate
            ) {
                PoseLandmarkerHelper(
                    context = context,
                    runningMode = RunningMode.LIVE_STREAM,
                    minPoseDetectionConfidence = viewModel.currentMinPoseDetectionConfidence,
                    minPoseTrackingConfidence = viewModel.currentMinPoseTrackingConfidence,
                    minPosePresenceConfidence = viewModel.currentMinPosePresenceConfidence,
                    currentModel = viewModel.currentModel,
                    currentDelegate = viewModel.currentDelegate,
                    poseLandmarkerHelperListener = object : PoseLandmarkerHelper.LandmarkerListener {
                        override fun onError(error: String, errorCode: Int) {
                            coroutineScope.launch {
                                Log.e("CameraScreen", "Erro no Pose Landmarker: $error")
                            }
                        }
                        override fun onResults(resultBundle: PoseLandmarkerHelper.ResultBundle) {
                            val landmarks = resultBundle.results.firstOrNull()?.landmarks()?.firstOrNull()
                            if (landmarks != null) {
                                viewModel.processPose(landmarks)
                            }
                            coroutineScope.launch {
                                poseResults = resultBundle
                            }
                        }
                    }
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                CameraPreview(
                    viewModel = viewModel,
                    poseLandmarkerHelper = poseLandmarkerHelper,
                )

                poseResults?.let {
                    val isFrontCamera = viewModel.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA
                    OverlayCanvas(
                        results = it.results.firstOrNull(),
                        imageHeight = it.inputImageHeight,
                        imageWidth = it.inputImageWidth,
                        runningMode = RunningMode.LIVE_STREAM,
                        isFrontCamera = isFrontCamera,
                        showLandmarks = viewModel.isRecording && viewModel.isFullyFramed
                    )
                }

                // Efeito de Borda Animada (Transparente -> Verde -> Vermelho)
                val borderColor by animateColorAsState(
                    targetValue = when {
                        viewModel.hasError -> Color.Red.copy(alpha = 0.4f)
                        viewModel.exerciceStage == 2 -> Color.Green.copy(alpha = 0.4f)
                        else -> Color.Transparent
                    },
                    animationSpec = androidx.compose.animation.core.tween(durationMillis = 300),
                    label = "BordaAnimada"
                )

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 45.dp.toPx()
                    drawRect(
                        color = borderColor,
                        topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                        size = Size(size.width - strokeWidth, size.height - strokeWidth),
                        style = Stroke(width = strokeWidth)
                    )
                }

                if (viewModel.hasError && viewModel.errorLocation != null){
                    Canvas(modifier = Modifier.fillMaxSize()){
                        drawCircle(
                            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                                colors = listOf(Color.Red.copy(alpha = 0.5f), Color.Transparent),
                                center = viewModel.errorLocation!!,
                                radius = 200f
                            ),
                            radius = 200f,
                            center = viewModel.errorLocation!!
                        )
                    }
                }

                if (viewModel.countDownTime > 0){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${viewModel.countDownTime}",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 120.sp, color = Color.White, fontWeight = FontWeight.Black
                            )
                        )
                    }
                }

                // 6. Barra Superior - Fundo Transparente
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp),
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text("Câmera", fontFamily = Raleway, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Row { }
                    }
                }

                // 7. Barra Inferior - Fundo Transparente
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(107.dp)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}, modifier = Modifier.size(39.dp)){
                            Icon(painter = painterResource(R.drawable.icon_galery), contentDescription = "Icon from gallery", tint = Color.White)
                        }

                        IconButton(
                            onClick = {
                                if (viewModel.isRecording) {
                                    viewModel.stopAnalysis(onNavigateToSummary = onFinishSession)
                                } else {
                                    viewModel.startAnalysis()
                                }
                            },
                            modifier = Modifier.size(68.dp)
                        ){
                            Icon(
                                painter = painterResource(R.drawable.icon_record),
                                contentDescription = "Icon from record",
                                tint = if (viewModel.isRecording) Color.Red else Color.White
                            )
                        }

                        IconButton(onClick = viewModel::switchCamera, modifier = Modifier.size(39.dp)){
                            Icon(imageVector = Icons.Default.Cached, contentDescription = "Inverter Câmera", tint = Color.White)
                        }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("A permissão da câmera é obrigatória.")
            }
        }
    }
}

@Composable
fun CameraPreview(viewModel: MainViewModel, poseLandmarkerHelper: PoseLandmarkerHelper) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    val currentCameraSelector = viewModel.cameraSelector
    val previewView = remember { PreviewView(context).apply { scaleType = PreviewView.ScaleType.FILL_CENTER } }

    DisposableEffect(lifecycleOwner, cameraExecutor, poseLandmarkerHelper, currentCameraSelector) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        val listener = Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also { it.surfaceProvider = previewView.surfaceProvider }
            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor) { imageProxy ->
                        val isFrontCamera = currentCameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA
                        poseLandmarkerHelper.detectLiveStream(imageProxy, isFrontCamera)
                    }
                }
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(lifecycleOwner, currentCameraSelector, preview, imageAnalyzer)
            } catch (exc: Exception) {
                Log.e("CameraScreen", "Use case binding failed", exc)
            }
        }
        cameraProviderFuture.addListener(listener, ContextCompat.getMainExecutor(context))
        onDispose {
            try {
                cameraProviderFuture.get().unbindAll()
            } catch (e: Exception) {
                Log.e("CameraScreen", "Error unbinding camera on dispose: ${e.message}")
            }
        }
    }
    AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
}