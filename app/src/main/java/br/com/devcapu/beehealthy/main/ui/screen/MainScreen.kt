package br.com.devcapu.beehealthy.main.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.authentication.login.ui.LoginActivity
import br.com.devcapu.beehealthy.common.ui.component.BottomBar
import br.com.devcapu.beehealthy.common.ui.component.TopBar
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.main.ui.HomeViewModel
import br.com.devcapu.beehealthy.main.ui.navigation.NavigationGraph
import br.com.devcapu.beehealthy.main.ui.state.HomeUIState

@Composable
fun MainScreen(viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MainScreen(state = state)
}

@Composable
fun MainScreen(state: HomeUIState) {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopBar { state.onClickLogout { logout(context) } }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
                onClick = state.onClickFAB
            ) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavigationGraph(
            navController = navController,
            state = state
        )
    }
}

private fun logout(context: Context) {
    val intent = LoginActivity.getIntent(context)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    context.startActivity(intent)
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainsScreenPreview() {
    BeeHealthyTheme {
        MainScreen(HomeUIState())
    }
}