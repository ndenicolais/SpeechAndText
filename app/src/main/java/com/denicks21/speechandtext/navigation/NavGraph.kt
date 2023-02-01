package com.denicks21.speechandtext.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denicks21.speechandtext.screens.HomeScreen
import com.denicks21.speechandtext.screens.SpeechToText
import com.denicks21.speechandtext.screens.SplashScreen
import com.denicks21.speechandtext.screens.TextToSpeech

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavScreens.Splash.route)
    {
        composable(route = NavScreens.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = NavScreens.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = NavScreens.SpeechToText.route){
            SpeechToText()
        }
        composable(route = NavScreens.TextToSpeech.route){
            TextToSpeech()
        }
    }
}