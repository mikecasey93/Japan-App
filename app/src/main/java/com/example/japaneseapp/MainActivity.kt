package com.example.japaneseapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.coreui.ui.theme.JapaneseAppTheme
import com.example.japaneseapp.bottombar.BottomBar
import com.example.japaneseapp.navigation.Navigation
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
                            { navController.navigate(route = "Chart") },
                            { navController.navigate(route = "Game") },
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