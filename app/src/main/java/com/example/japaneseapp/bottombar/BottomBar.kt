package com.example.japaneseapp.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Composable
fun BottomBar(
    navigateToHome: () -> Unit,
    navigateToChart: () -> Unit,
    navigateToGame: () -> Unit,
    navigateToTranslate: () -> Unit
) {
    val destinations = mapOf(
        "Home" to Pair(Icons.Default.Home, navigateToHome),
        "Chart" to Pair(Icons.Default.Book, navigateToChart),
        "Game" to Pair(Icons.Default.VideogameAsset, navigateToGame),
        "Translate" to Pair(Icons.Default.Translate, navigateToTranslate)
    )

    var selectedItem by remember { mutableStateOf("Home") }
    NavigationBar(containerColor = Color.White) {
        destinations.forEach { (label, navigate) ->
            NavigationBarItem(
                label = { Text(text = label, color = Color.Red) },
                selected = selectedItem == label,
                onClick = {
                    selectedItem = label
                    navigate.second()
                },
                icon = {
                    Icon(
                        imageVector = navigate.first,
                        contentDescription = label,
                        tint = Color.Red
                    )
                }
            )
        }
    }
}