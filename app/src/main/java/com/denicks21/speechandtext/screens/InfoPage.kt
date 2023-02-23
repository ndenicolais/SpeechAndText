package com.denicks21.speechandtext.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.R
import com.denicks21.speechandtext.ui.composables.CustomToolbar
import com.denicks21.speechandtext.ui.theme.GreyDark

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoPage(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Info application",
                openDrawer
            )
        },
        content = {
            Surface(
                color = Color.White,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.app_name
                                ),
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
                                modifier = Modifier.clip(CircleShape)
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Divider(
                                thickness = 1.dp,
                                color = GreyDark
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Text(
                                text = "Android application based on " +
                                        "Jetpack Compose with Speech to Text " +
                                        "and Text to Speech"
                            )
                        }
                    }
                }
            }
        }
    )
}