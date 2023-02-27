package com.denicks21.speechandtext.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denicks21.speechandtext.screen.*

@Composable
fun NavGraph(
    navController: NavHostController,
    openDrawer: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.IntroPage.route
    )
    {
        composable(route = NavScreens.IntroPage.route) {
            IntroPage(navController)
        }
        composable(route = NavScreens.HomePage.route) {
            HomePage(navController, openDrawer)
        }
        composable(route = NavScreens.SpeechToTextPage.route) {
            SpeechToTextPage(navController, openDrawer)
        }
        composable(route = NavScreens.TextToSpeechPage.route) {
            TextToSpeechPage(navController, openDrawer)
        }
        composable(route = NavScreens.InfoPage.route) {
            InfoPage(navController, openDrawer)
        }
    }
}