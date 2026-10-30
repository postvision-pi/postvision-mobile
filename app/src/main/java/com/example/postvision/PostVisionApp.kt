package com.example.postvision

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
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
import com.example.postvision.ui.repository.UserRepository
import com.example.postvision.ui.screens.ProfileViewModel
import com.example.postvision.ui.screens.WrapperProfile
import com.example.postvision.ui.screens.WrapperRegister
import com.example.postvision.ui.screens.WrapperSessionSummary
import com.example.postvision.ui.screens.WrapperSettings
import com.example.postvision.ui.screens.WrapperStatistic
import retrofit2.Retrofit
import com.example.postvision.ui.screens.camerax.MainViewModel as CameraViewModel
import com.example.postvision.ui.network.RetrofitClient
import com.example.postvision.ui.screens.ProfileViewModelFactory
import com.example.postvision.ui.screens.StatisticsViewModel
import androidx.lifecycle.ViewModelProvider


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
                },
                onNavigateToProfile = {
                    navController.navigate(NavRoutes.PROFILE)
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
            val statsViewModel: StatisticsViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return StatisticsViewModel(UserRepository(RetrofitClient.apiService)) as T
                    }
                }
            )

            val userId = mainViewModel.loggedUser?.id ?: ""
            val token = mainViewModel.authToken

            WrapperStatistic(
                viewModel = statsViewModel,
                mainViewModel = mainViewModel,
                userId = userId,
                token = token,
                onBottomTabClick = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(NavRoutes.PROFILE)
                }
            )

        }

        composable(NavRoutes.PROFILE) {
            val apiService = RetrofitClient.apiService
            val userRepository = UserRepository(apiService)

            val profileViewModel: ProfileViewModel = viewModel(
                factory = ProfileViewModelFactory(userRepository)
            )

            WrapperProfile(
                viewModel = profileViewModel,
                userEmail = mainViewModel.loggedUser?.email ?: "",
                token = mainViewModel.authToken,
                onBottomTabClick = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToSettings ={
                    navController.navigate(NavRoutes.SETTINGS)
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

        composable(NavRoutes.SETTINGS) {
            WrapperSettings(
                onNavigateProfile = { navController.navigate(NavRoutes.PROFILE) },
                onLogout = {
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}