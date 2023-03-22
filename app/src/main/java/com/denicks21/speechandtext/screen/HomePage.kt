package com.denicks21.speechandtext.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.navigation.NavScreens
import com.denicks21.speechandtext.navigation.NavScreens.HomePage.title
import com.denicks21.speechandtext.ui.composables.BackPress
import com.denicks21.speechandtext.ui.composables.TopBar
import com.denicks21.speechandtext.ui.theme.GreyDark
import com.denicks21.speechandtext.ui.theme.YellowDark

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    BackPress(onBackPressed = {})
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                title,
                openDrawer
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(NavScreens.SpeechToTextPage.route) },
                    modifier = Modifier
                        .width(180.dp)
                        .height(80.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(
                        5.dp,
                        YellowDark
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreyDark),
                ) {
                    Text(
                        text = "Speech to Text",
                        modifier = Modifier.padding(5.dp),
                        color = YellowDark,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = { navController.navigate(NavScreens.TextToSpeechPage.route) },
                    modifier = Modifier
                        .width(180.dp)
                        .height(80.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(
                        5.dp,
                        YellowDark
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreyDark),
                ) {
                    Text(
                        text = "Text to Speech",
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