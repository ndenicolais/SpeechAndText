package com.denicks21.speechandtext.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Source
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.MainActivity
import com.denicks21.speechandtext.navigation.NavScreens
import com.denicks21.speechandtext.ui.theme.Confirm
import com.denicks21.speechandtext.ui.theme.DarkGrey
import com.denicks21.speechandtext.ui.theme.DarkSurface
import com.denicks21.speechandtext.ui.theme.DarkText
import com.denicks21.speechandtext.ui.theme.LightSurface
import com.denicks21.speechandtext.ui.theme.LightText
import com.denicks21.speechandtext.ui.theme.LightYellow
import com.denicks21.speechandtext.ui.theme.Refuse
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SpeechToTextPage(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val speechContext = context as MainActivity
    val inputDialogState = remember { mutableStateOf(false) }
    val fileName = remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Speech to Text",
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = speechContext.speechInput.value,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FloatingActionButton(
                        onClick = { speechContext.askSpeechInput(context) },
                        backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = "Speak button",
                            tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow
                        )
                    }
                    FloatingActionButton(
                        onClick = {
                            if (speechContext.speechInput.value.isNotEmpty()) {
                                inputDialogState.value = true
                            } else {
                                Toast.makeText(
                                    context, "There are no text to save",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Save button",
                            tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow
                        )
                        if (inputDialogState.value) {
                            AlertDialog(
                                onDismissRequest = { inputDialogState.value = false },
                                title = {
                                    Text(
                                        text = "Filename",
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                text = {
                                    OutlinedTextField(
                                        value = fileName.value,
                                        onValueChange = { fileName.value = it },
                                        label = {
                                            Text(
                                                text = "Insert filename",
                                                color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                                            )
                                        },
                                        keyboardOptions = KeyboardOptions(
                                            capitalization = KeyboardCapitalization.Sentences,
                                            keyboardType = KeyboardType.Text,
                                            imeAction = ImeAction.Next
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onDone = {
                                                speechContext.speechInput.value.replaceFirstChar { it.uppercase() }
                                            }
                                        ),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            textColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                                            backgroundColor = if (isSystemInDarkTheme()) DarkSurface else LightSurface,
                                            focusedBorderColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                                            unfocusedBorderColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                                            placeholderColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                                        ),
                                    )
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            if (
                                                fileName.value.isNotEmpty()
                                                && speechContext.speechInput.value.isNotEmpty()
                                            ) {
                                                writeFile(
                                                    context,
                                                    fileName.value,
                                                    speechContext.speechInput.value
                                                )
                                                inputDialogState.value = false
                                                speechContext.speechInput.value = ""
                                                Toast.makeText(
                                                    context, "Saved",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {
                                                Toast.makeText(
                                                    context, "The field name is empty",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Confirm
                                        ),
                                    ) {
                                        Text(
                                            text = "Save",
                                            color = Color.White
                                        )
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { inputDialogState.value = false },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Refuse
                                        ),
                                    ) {
                                        Text(
                                            text = "Cancel",
                                            color = Color.White
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .clickable { navController.navigate(NavScreens.FileListPage.route) }
                        .width(160.dp)
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
                        Icon(
                            imageVector = Icons.Filled.Source,
                            contentDescription = "File list button",
                            tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow
                        )
                        Text(
                            text = "Filelist",
                            color = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

fun writeFile(context: Context, fileName: String, result: String) {
    val f = File(context.getExternalFilesDir("SpeechAndText"), "$fileName.txt")
    if (!f.exists()) {
        f.createNewFile()
    }
    val fileWriter = BufferedWriter(FileOutputStream(f).bufferedWriter())
    fileWriter.write(result)
    fileWriter.flush()
}