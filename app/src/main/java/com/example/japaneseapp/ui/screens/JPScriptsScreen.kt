package com.example.japaneseapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coreui.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPScriptsScreen() {
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
                        text = "Japanese consists of three different scripts",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ScriptCard(
                        title = "HIRAGANA: ひらか゛な",
                        info = "Hiragana is used for grammatical elements like " +
                                "particle verb endings, auxiliary verbs and " +
                                "native Japanese words with no Kanji"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ScriptCard(
                        title = "KATAKANA: カタカナ",
                        info = "Katakana is used for foreign words such as " +
                                "foreign names and words that have been borrowed " +
                                "from other languages"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ScriptCard(
                        title = "KANJI: かんし",
                        info = "Kanji are logographic Chinese characters, adapted " +
                                "from Chinese script"
                    )
                }
            }
        }
    )
}

@Composable
fun ScriptCard(
    title: String,
    info: String
) {
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
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
                Text(
                    text = info,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    maxLines = 3
                )
            }
        }
    }
}
