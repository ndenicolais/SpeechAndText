package com.denicks21.speechandtext.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denicks21.speechandtext.ui.theme.GreyDark
import com.denicks21.speechandtext.ui.theme.YellowDark
import java.util.*

@Composable
fun TextToSpeech() {
    var tts: TextToSpeech? = null
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState()
        var textFieldState by remember { mutableStateOf("") }
        var isBtnEnabled by remember { mutableStateOf(true) }
//        val scope = rememberCoroutineScope()
        var pitch by remember { mutableStateOf(1f) }
        var speechRate by remember { mutableStateOf(1f) }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "TextToSpeech",
                            modifier = Modifier.fillMaxWidth(),
                            color = GreyDark,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
        ) { it ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .padding(top = 50.dp)
                ) {
                    OutlinedTextField(
                        value = textFieldState,
                        onValueChange = { textFieldState = it },
                        label = {
                            Text(
                                text = "Text to convert",
                                color = GreyDark
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Enter some text here",
                                color = GreyDark
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = GreyDark,
                            backgroundColor = YellowDark,
                            focusedBorderColor = GreyDark,
                            unfocusedBorderColor = GreyDark,
                            placeholderColor = GreyDark
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Spacer(modifier = Modifier.height(35.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Pitch")
                        Slider(
                            value = pitch / 3,
                            onValueChange = { pitch = it * 3 },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Speed")
                        Slider(
                            value = speechRate / 3,
                            onValueChange = { speechRate = it * 3 },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 150.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                if (isBtnEnabled)
                                    isBtnEnabled = false
//                                scope.launch {
//                                    scaffoldState.snackbarHostState.showSnackbar(textFieldState)
//                                }
                                tts = TextToSpeech(
                                    context
                                ) {
                                    if (it == TextToSpeech.SUCCESS) {
                                        tts?.let { txtToSpeech ->
                                            txtToSpeech.language = Locale.ITALIAN
                                            txtToSpeech.setPitch(pitch)
                                            txtToSpeech.setSpeechRate(speechRate)
                                            txtToSpeech.speak(
                                                textFieldState,
                                                TextToSpeech.QUEUE_ADD,
                                                null,
                                                null
                                            )
                                        }
                                    }
                                }
                                isBtnEnabled = true
                            },
                            enabled = isBtnEnabled,
                            modifier = Modifier
                                .padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    bottom = 20.dp
                                )
                                .height(80.dp)
                                .width(80.dp)
                                .clip(RoundedCornerShape(45.dp)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = YellowDark
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Mic,
                                    contentDescription = "Speak",
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    tint = GreyDark
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}