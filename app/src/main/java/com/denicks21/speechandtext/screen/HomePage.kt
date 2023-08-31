package com.denicks21.speechandtext.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.R
import com.denicks21.speechandtext.navigation.NavScreens
import com.denicks21.speechandtext.ui.composables.BackPress
import com.denicks21.speechandtext.ui.theme.DarkGrey
import com.denicks21.speechandtext.ui.theme.DarkText
import com.denicks21.speechandtext.ui.theme.DarkYellow
import com.denicks21.speechandtext.ui.theme.LightText
import com.denicks21.speechandtext.ui.theme.LightYellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController) {
    var showInfoDialog by remember { mutableStateOf(false) }

    BackPress(onBackPressed = {})
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = if (isSystemInDarkTheme()) DarkText else LightText
                    )
                },
                actions = {
                    IconButton(
                        onClick = { showInfoDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info icon",
                            tint = if (isSystemInDarkTheme()) DarkText else LightText
                        )
                    }
                },
                backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .clickable { navController.navigate(NavScreens.SpeechToTextPage.route) }
                        .width(280.dp)
                        .height(120.dp),
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.stt),
                            contentDescription = "Speech to Text image",
                            modifier = Modifier.size(70.dp),
                            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) DarkGrey else DarkYellow)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Speech",
                                color = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = "Arrow icon",
                                tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                            )
                            Text(
                                text = "Text",
                                color = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Card(
                    modifier = Modifier
                        .clickable { navController.navigate(NavScreens.TextToSpeechPage.route) }
                        .width(280.dp)
                        .height(120.dp),
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tts),
                            contentDescription = "Text to Speech image",
                            modifier = Modifier.size(70.dp),
                            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) DarkGrey else DarkYellow)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Text",
                                color = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = "Arrow icon",
                                tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                            )
                            Text(
                                text = "Speech",
                                color = if (isSystemInDarkTheme()) DarkGrey else LightYellow,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
    if (showInfoDialog) {
        val uriHandler = LocalUriHandler.current

        Dialog(
            onDismissRequest = { showInfoDialog = false }
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .height(470.dp)
                    .width(450.dp),
                shape = RoundedCornerShape(size = 8.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { showInfoDialog = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close dialog"
                        )
                    }
                    Card(
                        modifier = Modifier
                            .height(400.dp)
                            .width(450.dp)
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = MaterialTheme.colors.onBackground,
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = if (isSystemInDarkTheme()) LightText else DarkText,
                                        shape = CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Divider(
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                thickness = 2.dp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = (
                                        "Android application built with Kotlin and Jetpack Compose " +
                                                "that shows how to use the functions:\n\n" +
                                                "Speech-to-Text (STT)\n\nText-to-Speech (TTS)"
                                        ),
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                thickness = 2.dp
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "My GitHub",
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = { uriHandler.openUri("https://github.com/ndenicolais") },
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.github_logo),
                                        contentDescription = "Open Github",
                                        colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) LightText else DarkText)
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Developed by DeNicks21",
                        color = if (isSystemInDarkTheme()) DarkText else LightText,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}