package com.example.japaneseapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.japaneseapp.ui.screens.HomeScreen
import com.example.japaneseapp.ui.screens.JPScriptsScreen
import com.example.japanviewmodel.viewmodel.JPViewModel
import com.example.screen.JPChartScreen
import com.example.screen.JPGameScreen
import com.example.screen.JPTranslateScreen

@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: JPViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            HomeScreen(navController)
        }
        composable(route = "Scripts") {
            JPScriptsScreen()
        }
        composable(route = "Game") {
            JPGameScreen(viewModel)
        }
        composable(route = "Chart") {
            JPChartScreen(viewModel)
        }
        composable(route = "Translate") {
            JPTranslateScreen(viewModel)
        }
    }
}