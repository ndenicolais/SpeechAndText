package com.denicks21.speechandtext.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Article
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.denicks21.speechandtext.ui.theme.DarkGrey
import com.denicks21.speechandtext.ui.theme.DarkText
import com.denicks21.speechandtext.ui.theme.LightText
import com.denicks21.speechandtext.ui.theme.LightYellow
import java.io.File

@Composable
fun FileListPage(
    navController: NavHostController,
    context: Context,
) {
    val savedFiles = remember { mutableStateListOf<String>() }
    val selectedFile = remember { mutableStateOf<String?>(null) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "File List",
                        color = if (isSystemInDarkTheme()) DarkText else LightText
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = if (isSystemInDarkTheme()) DarkText else LightText
                        )
                    }
                },
                backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            LaunchedEffect(Unit) {
                val folder = context.getExternalFilesDir("SpeechAndText")
                if (folder != null) {
                    val files = folder.listFiles()
                    if (files != null) {
                        savedFiles.clear()
                        files.forEach { file ->
                            savedFiles.add(file.name)
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(savedFiles) { fileName ->
                    Card(
                        modifier = Modifier
                            .clickable { selectedFile.value = fileName }
                            .width(180.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(50.dp),
                        backgroundColor = if (isSystemInDarkTheme()) LightYellow else DarkGrey,
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Article,
                                contentDescription = "File button",
                                tint = if (isSystemInDarkTheme()) DarkGrey else LightYellow
                            )
                            Text(
                                text = fileName,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                color = if (isSystemInDarkTheme()) DarkGrey else LightYellow
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp)
                    ) {
                        if (selectedFile.value == fileName) {
                            val fileContent = readFile(context, fileName)
                            Text(
                                text = fileContent,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                color = if (isSystemInDarkTheme()) LightYellow else DarkGrey
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun readFile(context: Context, fileName: String): String {
    val folder = context.getExternalFilesDir("SpeechAndText")
    val file = File(folder, fileName)
    return file.readText()
}