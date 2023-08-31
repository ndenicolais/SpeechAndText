package com.denicks21.speechandtext.screen

import android.annotation.SuppressLint
import android.net.Uri
import android.speech.tts.TextToSpeech
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.R
import com.denicks21.speechandtext.ui.theme.DarkGrey
import com.denicks21.speechandtext.ui.theme.DarkSurface
import com.denicks21.speechandtext.ui.theme.DarkText
import com.denicks21.speechandtext.ui.theme.LightSurface
import com.denicks21.speechandtext.ui.theme.LightText
import com.denicks21.speechandtext.ui.theme.LightYellow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TextToSpeechPage(navController: NavHostController) {
    var tts: TextToSpeech? = null
    val context = LocalContext.current
    var textFieldState by remember { mutableStateOf("") }
    var isBtnEnabled by remember { mutableStateOf(true) }
    var pitchRate by remember { mutableStateOf(1f) }
    var speechRate by remember { mutableStateOf(1f) }
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { selectedFileUri = it }
        }

    LaunchedEffect(selectedFileUri) {
        selectedFileUri?.let { uri ->
            try {
                val content = withContext(Dispatchers.IO) {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val stringBuilder = StringBuilder()
                    var line: String? = reader.readLine()
                    while (line != null) {
                        stringBuilder.append(line)
                        line = reader.readLine()
                    }
                    reader.close()
                    stringBuilder.toString()
                }
                textFieldState = content
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Text to Speech",
                        color = if (isSystemInDarkTheme()) DarkText else LightText
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = if (isSystemInDarkTheme()) DarkText else LightText
                        )
                    }
                },
                backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
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
                            color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter some text here",
                            color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                        backgroundColor = if (isSystemInDarkTheme()) DarkSurface else LightSurface,
                        focusedBorderColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                        unfocusedBorderColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                        placeholderColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 10.dp)
                )
                Spacer(modifier = Modifier.height(35.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pitch",
                        color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                    )
                    Slider(
                        value = pitchRate / 3,
                        onValueChange = { pitchRate = it * 3 },
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(
                            thumbColor = if (isSystemInDarkTheme()) LightText else DarkText,
                            activeTrackColor = if (isSystemInDarkTheme()) LightText else DarkText,
                            inactiveTrackColor = if (isSystemInDarkTheme()) DarkText else LightText
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Speed",
                        color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                    )
                    Slider(
                        value = speechRate / 3,
                        onValueChange = { speechRate = it * 3 },
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(
                            thumbColor = if (isSystemInDarkTheme()) LightText else DarkText,
                            activeTrackColor = if (isSystemInDarkTheme()) LightText else DarkText,
                            inactiveTrackColor = if (isSystemInDarkTheme()) DarkText else LightText
                        )
                    )
                }
                Spacer(modifier = Modifier.height(35.dp))
                Card(
                    modifier = Modifier
                        .clickable { launcher.launch("text/plain") }
                        .width(120.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(50.dp),
                    backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.import_file),
                            contentDescription = "Import file image",
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) DarkGrey else LightYellow)
                        )
                        Text(
                            text = "Import file",
                            color = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FloatingActionButton(
                        onClick = {
                            if (isBtnEnabled)
                                isBtnEnabled = false
                            tts = TextToSpeech(
                                context
                            ) {
                                if (it == TextToSpeech.SUCCESS) {
                                    tts?.let { txtToSpeech ->
                                        txtToSpeech.language = Locale.ENGLISH
                                        txtToSpeech.setPitch(pitchRate)
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
                        backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = "Speak button",
                            tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow
                        )
                    }
                }
            }
        }
    }
}