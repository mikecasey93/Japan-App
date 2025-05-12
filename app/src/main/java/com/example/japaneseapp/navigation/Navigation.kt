package com.example.japaneseapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.japaneseapp.ui.screens.HomeScreen
import com.example.japaneseapp.ui.screens.JPScriptsScreen
import com.example.japanviewmodel.viewmodel.JPCharactersViewModel
import com.example.japanviewmodel.viewmodel.JPGameViewModel
import com.example.japanviewmodel.viewmodel.JPSharedViewModel
import com.example.japanviewmodel.viewmodel.JPTranslateViewModel
import com.example.screen.JPChartScreen
import com.example.screen.JPGameScreen
import com.example.screen.JPTranslateScreen

@Composable
fun Navigation(navController: NavHostController) {
    val sharedViewModel: JPSharedViewModel = hiltViewModel()
    val charViewModel: JPCharactersViewModel = hiltViewModel()
    val gameViewModel: JPGameViewModel = hiltViewModel()
    val translateViewModel: JPTranslateViewModel = hiltViewModel()

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
        composable(route = "Chart") {
            JPChartScreen(
                charViewModel = charViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = "Game") {
            JPGameScreen(gameViewModel)
        }
        composable(route = "Translate") {
            JPTranslateScreen(
                sharedViewModel = sharedViewModel,
                translateViewModel = translateViewModel
            )
        }
    }
}