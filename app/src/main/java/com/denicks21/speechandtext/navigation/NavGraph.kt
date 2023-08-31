package com.denicks21.speechandtext.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denicks21.speechandtext.screen.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.IntroPage.route
    )
    {
        composable(route = NavScreens.IntroPage.route) {
            IntroPage(navController)
        }
        composable(route = NavScreens.HomePage.route) {
            HomePage(navController)
        }
        composable(route = NavScreens.SpeechToTextPage.route) {
            SpeechToTextPage(navController)
        }
        composable(route = NavScreens.TextToSpeechPage.route) {
            TextToSpeechPage(navController)
        }
        composable(route = NavScreens.FileListPage.route) {
            val context = LocalContext.current
            FileListPage(navController, context)
        }
    }
}