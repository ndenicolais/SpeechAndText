package com.denicks21.speechandtext.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.MainActivity
import com.denicks21.speechandtext.navigation.NavScreens.SpeechToTextPage.title
import com.denicks21.speechandtext.ui.composables.CustomTopBar
import com.denicks21.speechandtext.ui.theme.GreyDark
import com.denicks21.speechandtext.ui.theme.YellowDark
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SpeechToTextPage(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    val context = LocalContext.current
    val speechContext = context as MainActivity
    val scaffoldState = rememberScaffoldState()
    val inputDialogState = remember { mutableStateOf(false) }
    val fileName = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopBar(
                title,
                openDrawer
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    speechContext.speechInput.value,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 30.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FloatingActionButton(
                        onClick = {
                            speechContext.askSpeechInput(context)
                        },
                        backgroundColor = YellowDark,
                        contentColor = GreyDark
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = "Speak"
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
                        backgroundColor = YellowDark,
                        contentColor = GreyDark
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Save file"
                        )
                        if (inputDialogState.value) {
                            AlertDialog(
                                onDismissRequest = {
                                    inputDialogState.value = false
                                },
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
                                                color = GreyDark
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
                                            textColor = GreyDark,
                                            backgroundColor = YellowDark,
                                            focusedBorderColor = GreyDark,
                                            unfocusedBorderColor = GreyDark,
                                            placeholderColor = GreyDark
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
                                                writeToFile(
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
                                            backgroundColor = GreyDark
                                        ),
                                    ) {
                                        Text(
                                            text = "Save",
                                            color = YellowDark
                                        )
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = {
                                            inputDialogState.value = false
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = GreyDark
                                        ),
                                    ) {
                                        Text(
                                            text = "Cancel",
                                            color = YellowDark
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun writeToFile(context: Context, fileName: String, result: String) {
    val f = File(context.getExternalFilesDir("SpeechAndText"), "$fileName.txt")
    if (!f.exists()) {
        f.createNewFile()
    }
    val fileWriter = BufferedWriter(FileOutputStream(f).bufferedWriter())
    fileWriter.write(result)
    fileWriter.flush()
}