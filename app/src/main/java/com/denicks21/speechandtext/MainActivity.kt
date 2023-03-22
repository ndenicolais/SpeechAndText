package com.denicks21.speechandtext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.denicks21.speechandtext.navigation.NavGraph
import com.denicks21.speechandtext.ui.composables.NavigationDrawer
import com.denicks21.speechandtext.ui.theme.GreyDark
import com.denicks21.speechandtext.ui.theme.SpeechAndTextTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    var speechInput = mutableStateOf("")

    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeechAndTextTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setNavigationBarColor(
                        GreyDark,
                        darkIcons = false
                    )
                }
                val navController = rememberAnimatedNavController()

                Surface {
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                    ModalDrawer(
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen,
                        drawerContent = {
                            NavigationDrawer(
                                onDestinationClicked = { route ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(route) {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    ) {
                        NavGraph(
                            navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                }
            }
        }
    }

    fun askSpeechInput(context: Context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            Toast.makeText(context, "Speech not Available", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Talk")
            startActivityForResult(intent, 102)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            speechInput.value = result?.get(0).toString()
        }
    }
}