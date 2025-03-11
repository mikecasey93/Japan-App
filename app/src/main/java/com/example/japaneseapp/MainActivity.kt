package com.example.japaneseapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.coreui.ui.theme.JapaneseAppTheme
import com.example.japaneseapp.bottombar.BottomBar
import com.example.japaneseapp.navigation.Navigation
import com.example.japanviewmodel.viewmodel.JPViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            { navController.navigate(route = "Home") },
                            { navController.navigate(route = "Game") },
                            { navController.navigate(route = "Chart") },
                            { navController.navigate(route = "Translate") }
                        )
                    },
                    content = {
                        Navigation(navController)
                    }
                )
            }
        }
    }
}