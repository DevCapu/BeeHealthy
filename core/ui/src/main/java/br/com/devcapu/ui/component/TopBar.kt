package br.com.devcapu.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.devcapu.ui.theme.BeeHealthyTheme

@Composable
fun TopBar(onClickSignOff: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        title = { Text(text = "Bee Healthy") },
        actions = {
            IconButton(onClick = {
                Log.i("TAG", "TopBar: CLICOU")}) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout"
                )
            }
        }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    br.com.devcapu.ui.theme.BeeHealthyTheme { TopBar { } }
}