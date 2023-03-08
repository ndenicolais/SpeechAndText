package com.denicks21.speechandtext.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreens(val title: String, val route: String, var icon: ImageVector) {
    object IntroPage : NavScreens(
        "Intro", "IntroPage", Icons.Default.Android
    )

    object HomePage : NavScreens(
        "Home", "HomePage", Icons.Default.Home
    )

    object SpeechToTextPage : NavScreens(
        "Speech to Text", "SpeechToTextPage", Icons.Default.RecordVoiceOver
    )

    object TextToSpeechPage : NavScreens(
        "Text to Speech", "TextToSpeechPage", Icons.Default.SettingsVoice
    )

    object InfoPage: NavScreens(
        "Info", "InfoPage", Icons.Default.Info
    )
}