package com.example.myapplication55.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication55.R
import com.example.myapplication55.presentation.auth.CreateAccount
import com.example.myapplication55.presentation.auth.CreatePass
import com.example.myapplication55.presentation.auth.Welcome
import com.example.myapplication55.presentation.project.CreateProject
import com.example.myapplication55.presentation.project.ProjectList
import com.example.myapplication55.viewModel.AuthViewModel
import com.example.uikit.AppBottomBar
import com.example.uikit.BottomNavItemData
import com.example.uikit.Project
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val bottomItems = listOf(
        BottomNavItemData("homepage", "Главная", iconRes = R.drawable.home),
        BottomNavItemData("catalog", "Каталог", iconRes = R.drawable.catalog),
        BottomNavItemData("project", "Проекты", iconRes = R.drawable.project),
        BottomNavItemData("profile", "Профиль", iconRes = R.drawable.user)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val startDestination ="homepage"
//    val startDestination = if (viewModel.isUserLoggedIn()) "homepage" else "welcome"

    Scaffold(
        bottomBar = {
            if (bottomItems.any { it.route == currentRoute }) {
                AppBottomBar(
                    items = bottomItems,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
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

            composable("create_account") { CreateAccount(navController = navController, viewModel = viewModel) }

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
            composable("homepage") { Homepage(navController) }
            composable("catalog") { Catalog(navController = navController, viewModel = koinViewModel()) }
            composable("cart") { CardInCartScreen(navController = navController) }
            composable("project") { ProjectList(navController) }
            composable("createProject") { CreateProject(navController) }
            composable("profile") { Profile(navController, viewModel) }
        }
    }
}
