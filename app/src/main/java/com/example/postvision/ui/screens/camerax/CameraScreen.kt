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
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
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
// IMPORT DO ÍCONE DE INVERSÃO
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.* // Importa tudo de compose.runtime
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.launch // Import necessário para o coroutineScope.launch
import java.util.concurrent.Executors

class CameraScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CameraScreenContent(viewModel: MainViewModel) {
        val context = LocalContext.current
        var hasPermission by remember {
            mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                hasPermission = isGranted
            }
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

            // 1. CORREÇÃO: Criação do PoseLandmarkerHelper com o listener no construtor.
            val poseLandmarkerHelper: PoseLandmarkerHelper = remember(
                viewModel.currentMinPoseDetectionConfidence,
                viewModel.currentMinPoseTrackingConfidence,
                viewModel.currentMinPosePresenceConfidence,
                viewModel.currentModel,
                viewModel.currentDelegate
            ) {
                // Cria o helper, passando o listener aqui:
                PoseLandmarkerHelper(
                    context = context,
                    runningMode = RunningMode.LIVE_STREAM,
                    minPoseDetectionConfidence = viewModel.currentMinPoseDetectionConfidence,
                    minPoseTrackingConfidence = viewModel.currentMinPoseTrackingConfidence,
                    minPosePresenceConfidence = viewModel.currentMinPosePresenceConfidence,
                    currentModel = viewModel.currentModel,
                    currentDelegate = viewModel.currentDelegate,
                    // Listener (Callback)
                    poseLandmarkerHelperListener = object : PoseLandmarkerHelper.LandmarkerListener {
                        override fun onError(error: String, errorCode: Int) {
                            coroutineScope.launch {
                                Log.e("CameraScreen", "Erro no Pose Landmarker: $error")
                            }
                        }

                        override fun onResults(resultBundle: PoseLandmarkerHelper.ResultBundle) {
                            // --- ADICIONE ESTE BLOCO ABAIXO ---
                            val landmarks = resultBundle.results.firstOrNull()?.landmarks()?.firstOrNull()
                            if (landmarks != null) {
                                // Envia os pontos para o cérebro do app (ViewModel)
                                viewModel.processPose(landmarks)
                            } else {
                                // Caso a câmera não veja ninguém, avisa no console
                                Log.w("PI_MONITOR", "Aviso: Nenhum corpo detectado no campo de visão.")
                            }
                            // ----------------------------------
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

                val borderColor by animateColorAsState(
                    targetValue = when {
                        viewModel.hasError -> androidx.compose.ui.graphics.Color.Red.copy(alpha = 0.5f)
                        viewModel.exerciceStage == 2 -> androidx.compose.ui.graphics.Color.Green.copy(alpha = 0.4f)
                        else -> androidx.compose.ui.graphics.Color.Transparent
                    },
                    animationSpec = androidx.compose.animation.core.tween(500)
                )
                androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                    drawRect(
                        color = borderColor,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 20.dp.toPx())
                    )
                }

                if (viewModel.hasError && viewModel.errorLocation != null){
                    androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()){
                        drawCircle(
                            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                                colors = listOf(androidx.compose.ui.graphics.Color.Red.copy(alpha = 0.4f), androidx.compose.ui.graphics.Color.Transparent),
                                center = viewModel.errorLocation!!,
                                radius = 250f
                            ),
                            radius = 250f,
                            center = viewModel.errorLocation!!
                        )
                    }
                }

                // Renderiza a sobreposição do MediaPipe
                poseResults?.let {
                    // Determina se a câmera frontal está em uso para o efeito espelho no Canvas
                    val isFrontCamera = viewModel.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA

                    OverlayCanvas(
                        results = it.results.firstOrNull(),
                        imageHeight = it.inputImageHeight,
                        imageWidth = it.inputImageWidth,
                        runningMode = RunningMode.LIVE_STREAM,
                        isFrontCamera = isFrontCamera // Passa a info de reflexão
                    )
                }

                if (viewModel.countDownTime > 0){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.7f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${viewModel.countDownTime}",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 120.sp, color = androidx.compose.ui.graphics.Color.White, fontWeight = FontWeight.Black
                            )
                        )
                    }
                }

                // Barra superior (Câmera)
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp),
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            "Câmera",
                            fontFamily = Raleway,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            modifier = Modifier
                                .padding(end = 18.dp),
                            text = "SQUATS: ${viewModel.squatCount}",
                            fontFamily = Raleway,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }


                }

                // Card Inferior com botões
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(107.dp)
                    .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        // Botão Galeria
                        IconButton(
                            onClick = {},
                            modifier = Modifier.size(39.dp)
                        ){
                            Icon(
                                painter = painterResource(R.drawable.icon_galery),
                                contentDescription = "Icon from gallery",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        // Botão Gravar
                        IconButton(
                            onClick = { viewModel.startAnalysis() },
                            modifier = Modifier.size(68.dp)
                        ){
                            Icon(
                                painter = painterResource(R.drawable.icon_record),
                                contentDescription = "Icon from record",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        // Botão de inversão de câmera
                        IconButton(
                            onClick = viewModel::switchCamera, // Chama a função no ViewModel
                            modifier = Modifier.size(39.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Cached, // Ícone padrão para inversão
                                contentDescription = "Inverter Câmera",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }

        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Camera permission is required.")
            }
        }
    }
}

/**
 * Muestra la vista previa de la cámara y analiza las imágenes en tiempo real para detectar poses.
 *
 * @param viewModel El ViewModel que contém a configuração e o seletor de câmera.
 * @param poseLandmarkerHelper O helper já configurado para detecção de pose.
 */
@Composable
fun CameraPreview(
    viewModel: MainViewModel,
    poseLandmarkerHelper: PoseLandmarkerHelper
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    // O seletor de câmera é observado do ViewModel
    val currentCameraSelector = viewModel.cameraSelector

    // A PreviewView é criada e lembrada no Composable
    val previewView = remember {
        PreviewView(context).apply {
            scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }

    // O DisposableEffect é reexecutado quando currentCameraSelector muda
    // Ele é responsável por desvincular a câmera antiga e vincular a nova
    DisposableEffect(
        lifecycleOwner, cameraExecutor, poseLandmarkerHelper, currentCameraSelector
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        val listener = Runnable {
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

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
                // CHAVE: DESVINCULA TUDO ANTES DE VINCULAR A NOVA CÂMERA
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    currentCameraSelector, // Usa o seletor atual do ViewModel
                    preview,
                    imageAnalyzer
                )
            } catch (exc: Exception) {
                Log.e("CameraScreen", "Use case binding failed", exc)
            }
        }

        cameraProviderFuture.addListener(listener, ContextCompat.getMainExecutor(context))

        // No onDispose (quando o seletor mudar ou o Composable sair da tela), desvincula.
        onDispose {
            try {
                // A desvinculação da câmera é mantida para evitar o TimeoutException,
                // mas a remoção do listener (que estava dando problema) foi removida.
                cameraProviderFuture.get().unbindAll()
            } catch (e: Exception) {
                // Captura exceções se o provider ainda não estiver pronto.
                Log.e("CameraScreen", "Error unbinding camera on dispose: ${e.message}")
            }
        }
    }

    // AndroidView para hospedar a PreviewView do CameraX
    AndroidView(
        factory = { previewView }, // Usa a PreviewView já criada no remember
        modifier = Modifier.fillMaxSize()
    )
}