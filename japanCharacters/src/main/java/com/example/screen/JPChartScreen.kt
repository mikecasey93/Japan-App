package com.example.screen

import androidx.compose.foundation.lazy.items
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.japanviewmodel.viewmodel.JPCharactersViewModel
import com.example.japanviewmodel.viewmodel.JPSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPChartScreen(
    charViewModel: JPCharactersViewModel,
    sharedViewModel: JPSharedViewModel,
) {
    val hiragana = charViewModel.hiragana
    val katakana = charViewModel.katakana
    var charList by remember { mutableStateOf("hiragana") }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        sharedViewModel.initTextToSpeech(context)
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
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "SELECT SCRIPT",
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ScriptSelector(
                        selected = charList,
                        onSelectedChange = { charList = it}
                    )

                    Text(
                        text = "(CLICK FOR PRONUNCIATION)",
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val characters = when (charList) {
                            "hiragana" -> hiragana.orEmpty()
                            "katakana" -> katakana.orEmpty()
                            else -> emptyList()
                        }
                        CharacterCharList(
                            characters = characters,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ScriptSelector(
    selected: String,
    onSelectedChange: (String) -> Unit
) {
    val options = listOf("hiragana", "katakana")

    Row(verticalAlignment = Alignment.CenterVertically) {
        options.forEach { option ->
            RadioButton(
                selected = selected == option,
                onClick = { onSelectedChange(option) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Red,
                    unselectedColor = Color.Black,
                    disabledSelectedColor = Color.Black,
                    disabledUnselectedColor = Color.Black
                )
            )
            Text(
                text = option.uppercase(),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun CharacterCharList(
    characters: List<Pair<String, String>>,
    sharedViewModel: JPSharedViewModel
) {
    LazyColumn(
       modifier = Modifier.fillMaxWidth() ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters) { item ->
            CardItem(
                item.first,
                item.second,
                sharedViewModel
            )
        }
    }
}

@Composable
fun CardItem(
    first: String,
    second: String,
    sharedViewModel: JPSharedViewModel
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(40.dp)
            .clickable {
                sharedViewModel.speakText(second)
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = first,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Text(
                    text =": ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Text(
                    text = second,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }
    }
}