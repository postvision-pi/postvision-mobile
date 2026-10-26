package com.example.postvision

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.screens.WrapperChooseExercise
import com.example.postvision.ui.screens.WrapperHome
import com.example.postvision.ui.screens.WrapperLogin
import com.example.postvision.ui.screens.WrapperStepByStep
import com.example.postvision.ui.screens.camerax.CameraScreen
import com.example.postvision.MainViewModel
import com.example.postvision.ui.screens.WrapperProfile
import com.example.postvision.ui.screens.WrapperRegister
import com.example.postvision.ui.screens.WrapperSessionSummary
import com.example.postvision.ui.screens.WrapperStatistic
import com.example.postvision.ui.screens.camerax.MainViewModel as CameraViewModel


@Composable
fun AppNavigation(mainViewModel: MainViewModel = viewModel()) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN,
        modifier = Modifier.fillMaxSize(),
    ) {

        composable(NavRoutes.LOGIN) {
            WrapperLogin(
                viewModel = mainViewModel,
                onNavigateToHome = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavRoutes.REGISTER) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
            )
        }

        composable(NavRoutes.HOME) {
            WrapperHome(
                viewModel = mainViewModel,
                onNavigateToChooseExercise = {
                    navController.navigate(NavRoutes.CHOOSE_EXERCISE)
                },
                onBottomTabClick = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                }
            )
        }

        composable(NavRoutes.CHOOSE_EXERCISE) {
            WrapperChooseExercise(
                onNavigateToStepByStep = {
                    navController.navigate(NavRoutes.STEP_BY_STEP)
                }
            )
        }

        composable(NavRoutes.STEP_BY_STEP) {
            WrapperStepByStep(
                onNavigateToCameraX = {
                    navController.navigate(NavRoutes.CAMERA_SCREEN)
                }
            )
        }

        composable(NavRoutes.CAMERA_SCREEN) {
            val cameraVM: CameraViewModel = viewModel()
            CameraScreen().CameraScreenContent(viewModel = cameraVM)
        }

        composable(NavRoutes.STATISTICS) {
            WrapperStatistic(
                onBottomTabClick = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(NavRoutes.PROFILE) {
            WrapperProfile(
                onBottomTabClick = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(NavRoutes.SESSION_SUMMARY) {
            WrapperSessionSummary(
                viewModel = mainViewModel,
                onNavigateToChooseExercise = {
                    navController.navigate(NavRoutes.CHOOSE_EXERCISE)
                },
                onBottomTabClick = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(NavRoutes.REGISTER) {
            WrapperRegister(
                onNavigateToLogin = {
                    navController.navigate(NavRoutes.LOGIN)
                },
                onNavigateToHome = {
                    navController.navigate(NavRoutes.HOME)
                }
            )
        }
    }
}