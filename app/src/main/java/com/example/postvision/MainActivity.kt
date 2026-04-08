// MainActivity.kt
package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.postvision.ui.screens.camerax.CameraScreen
import com.example.postvision.ui.screens.camerax.MainViewModel
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.screens.WrapperChooseExercise
import com.example.postvision.ui.screens.WrapperHome
import com.example.postvision.ui.screens.WrapperLogin
import com.example.postvision.ui.screens.WrapperStepByStep
import com.example.postvision.ui.theme.PostVisionTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

