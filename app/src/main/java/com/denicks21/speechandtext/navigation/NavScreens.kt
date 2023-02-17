package com.denicks21.speechandtext.navigation

sealed class NavScreens(val route: String) {
    object Splash : NavScreens("Splashscreen")
    object Home : NavScreens("Homescreen")
    object SpeechToText : NavScreens("SpeechToTextscreen")
    object TextToSpeech : NavScreens("TextToSpeechscreen")
}