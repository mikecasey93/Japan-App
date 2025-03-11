package com.example.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coreui.ui.theme.background
import com.example.japanviewmodel.viewmodel.JPViewModel
import com.example.utils.LanguageUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPTranslateScreen(viewModel: JPViewModel) {
    var userInput by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }
    var romajiText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.initTextToSpeech(context)
    }

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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ENTER TEXT TO",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "TRANSLATE",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = userInput,
                        onValueChange = {
                            userInput = it
                        },
                        singleLine = false,
                        maxLines = 3,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            disabledTextColor = Color.Gray,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    Button(
                        onClick = {
                            if (userInput.isBlank()) {
                                outputText = "Please enter text to translate"
                                return@Button
                            }

                            isLoading = true
                            LanguageUtils.translationInit(userInput) { translatedText ->
                                isLoading = false
                                outputText = translatedText
                                romajiText = LanguageUtils.convertToRomaji(translatedText)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Translate",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    AnimatedVisibility(
                        visible = isLoading
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }

                    AnimatedVisibility(
                        visible = outputText.isNotEmpty()
                    ) {
                        Column {
                            Text(
                                text = outputText,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = romajiText,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "Speech Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    viewModel.speakText(outputText)
                                }
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}