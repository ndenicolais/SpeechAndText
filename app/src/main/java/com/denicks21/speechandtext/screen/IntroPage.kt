package com.denicks21.speechandtext.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.denicks21.speechandtext.R
import com.denicks21.speechandtext.ui.theme.DarkGrey
import com.denicks21.speechandtext.ui.theme.DarkSurface
import com.denicks21.speechandtext.ui.theme.LightSurface
import com.denicks21.speechandtext.ui.theme.LightYellow
import kotlinx.coroutines.delay

@Composable
fun IntroPage(navController: NavController) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = { OvershootInterpolator(4f).getInterpolation(it) }
            )
        )
        delay(500L)
        navController.navigate("HomePage")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) DarkSurface else LightSurface)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                color = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .scale(scale.value)
                    .clip(CircleShape)
            )
        }
    }
}