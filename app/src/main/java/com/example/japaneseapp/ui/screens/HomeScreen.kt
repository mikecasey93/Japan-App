package com.example.japaneseapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coreui.ui.theme.Typography
import com.example.coreui.ui.theme.background
import com.example.japaneseapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        text = "Japan App",
                        fontFamily = FontFamily.Cursive,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                },
                modifier = Modifier.systemBarsPadding()
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(background)
                    .padding(paddingValues)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "KON'NICHIWA YUZA",
                        textAlign = TextAlign.Center,
                        style = Typography.labelLarge,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Image(
                        painter = painterResource(R.drawable.japan_flag),
                        contentDescription = "Japanese Flag",
                        modifier = Modifier.size(150.dp)
                    )

                    Text(
                        text = "Welcome to the Japan App where you can practice " +
                            "Japanese scripts Hiragana and Katakana using the " +
                            "different features Game, Chart and Translate so let's learn!!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CardText(text = "Game Feature allows you to guess randomly " +
                            "generated Hiragana and Katakana characters"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CardText(text = "Chart Feature allows you to study " +
                            "the Hiragana and Katakana scripts " +
                            "and their pronunciations "
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CardText(text = "Translate Feature lets you translate " +
                            "a given text to Japanese and displays " +
                            "romaji for easier reading"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "About Japanese (Click Here)",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { navController.navigate("Scripts") }
                    )
                }
            }
        }
    )
}

@Composable
fun CardText(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                maxLines = 3
            )
        }
    }
}