package com.denicks21.speechandtext.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.denicks21.speechandtext.ui.theme.GreyDark

@Composable
fun TopBar(
    title: String,
    onButtonClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = GreyDark,
                fontSize = 22.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Drawer Menu",
                    tint = GreyDark
                )
            }
        }
    )
}