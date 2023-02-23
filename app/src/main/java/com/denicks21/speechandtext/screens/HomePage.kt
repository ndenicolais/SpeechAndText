package com.denicks21.speechandtext.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.BuildConfig
import com.denicks21.speechandtext.navigation.NavScreens
import com.denicks21.speechandtext.ui.composables.CustomToolbar
import com.denicks21.speechandtext.ui.theme.GreyDark
import com.denicks21.speechandtext.ui.theme.YellowDark

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomToolbar(
                title = "Home",
                openDrawer
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(NavScreens.SpeechToTextPage.route)
                    },
                    modifier = Modifier
                        .width(220.dp)
                        .height(80.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GreyDark
                    ),
                ) {
                    Text(
                        text = "SpeechToText",
                        modifier = Modifier.padding(5.dp),
                        color = YellowDark,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(NavScreens.TextToSpeechPage.route)
                    },
                    modifier = Modifier
                        .width(220.dp)
                        .height(80.dp)
                        .padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GreyDark
                    ),
                ) {
                    Text(
                        text = "TextToSpeech",
                        modifier = Modifier.padding(5.dp),
                        color = YellowDark,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}