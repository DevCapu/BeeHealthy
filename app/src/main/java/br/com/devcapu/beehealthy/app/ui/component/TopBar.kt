package br.com.devcapu.beehealthy.app.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopBar(onClickSignOff: () -> Unit) {
    TopAppBar(
        backgroundColor = Color.White,
        title = { Text(text = "Bee Healthy") },
        actions = {
            IconButton(onClick = onClickSignOff) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout"
                )
            }
        }

    )
}