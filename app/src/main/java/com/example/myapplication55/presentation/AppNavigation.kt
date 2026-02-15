package com.example.myapplication55.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication55.presentation.auth.CreateAccount
import com.example.myapplication55.presentation.auth.CreatePass
import com.example.myapplication55.presentation.auth.Welcome
import com.example.myapplication55.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AuthViewModel = koinViewModel()
) {
    // 1. Проверяем, залогинен ли юзер, чтобы выбрать стартовый экран
    val startDestination = if (viewModel.isUserLoggedIn()) "homepage" else "welcome"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ЭКРАН 1: Вход (Welcome)
        composable("welcome") {
            Welcome(
                navController = navController,
                viewModel = viewModel,
                onNavigateToHome = {
                    navController.navigate("homepage") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("create_account")
                }
            )
        }

        // ЭКРАН 2: Сбор данных профиля
        composable("create_account") {
            CreateAccount(
                navController = navController,
                viewModel = viewModel
            )
        }

        // ЭКРАН 3: Создание пароля и Финальная регистрация
        composable("create_pass") {
            CreatePass(
                navController = navController,
                viewModel = viewModel,
                onSuccess = {
                    navController.navigate("homepage") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // ЭКРАН 4: Главная
        composable("homepage") {
            Homepage()
        }
    }
}