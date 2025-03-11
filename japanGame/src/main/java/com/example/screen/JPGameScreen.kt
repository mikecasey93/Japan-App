package com.example.screen

import android.content.Intent
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.coreui.ui.theme.background
import com.example.game.R
import com.example.japanviewmodel.viewmodel.JPViewModel
import com.example.service.JapanService
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPGameScreen(viewModel: JPViewModel) {
    val selectedScript by viewModel.selectedScript.collectAsState()
    val selectedSyllable by viewModel.selectedPair.collectAsState()
    val answerStatus by viewModel.answerStatus.collectAsState()
    val score by viewModel.score.collectAsState()
    val scoreMessage by viewModel.scoreMessage.collectAsState()
    val textInput by viewModel.textInput.collectAsState()

    val confetti by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.confetti))
    var showAnimation by remember { mutableStateOf(false) }
    val hasPlayedConfetti = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(score) {
        if (score > 0 && score % 5 == 0 && !hasPlayedConfetti.value) {
            showAnimation = true
            hasPlayedConfetti.value = true

            val intent = Intent(context, JapanService::class.java).apply {
                action = "PLAY"
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }

            delay(5000)
            showAnimation = false

            val stopIntent = Intent(context, JapanService::class.java).apply {
                action = "STOP"
            }
            context.stopService(stopIntent)
        } else if (score % 5 != 0) {
            hasPlayedConfetti.value = false
        }
    }

    LaunchedEffect(answerStatus, scoreMessage) {
        if (answerStatus.isNotEmpty()) {
            delay(2000)
            viewModel.clearAnswerStatus()
        }
        if (scoreMessage.isNotEmpty()) {
            delay(2000)
            viewModel.clearScoreMessage()
        }
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {

                    Text(
                        text = "NAME THIS $selectedScript",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "CHARACTER",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = selectedSyllable.second,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = textInput,
                        onValueChange = { viewModel.changeInput(it) },
                        maxLines = 1,
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.verifyCharacter(textInput) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Check",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            viewModel.randomPair()
                            viewModel.resetTextInput()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Reset",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = answerStatus.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500))
                    ) {
                        Text(
                            text = answerStatus,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    AnimatedVisibility(
                        visible = showAnimation,
                        enter = fadeIn(initialAlpha = 0.4f),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500))
                    ) {
                        LottieAnimation(
                            composition = confetti,
                            iterations = 1,
                            modifier = Modifier.size(250.dp)
                        )
                    }

                    AnimatedVisibility(
                        visible = scoreMessage.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500))
                    ) {
                        Text(
                            text = scoreMessage,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}