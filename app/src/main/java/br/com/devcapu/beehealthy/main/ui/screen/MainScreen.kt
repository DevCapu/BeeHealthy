package br.com.devcapu.beehealthy.main.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.common.ui.component.BottomBar
import br.com.devcapu.beehealthy.common.ui.component.TopBar
import br.com.devcapu.beehealthy.login.ui.LoginActivity
import br.com.devcapu.beehealthy.main.ui.HomeUIState
import br.com.devcapu.beehealthy.main.ui.HomeViewModel
import br.com.devcapu.beehealthy.main.ui.navigation.NavigationGraph

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
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopBar { state.onClickLogout { logout(context) } }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color(0xFF8463E5),
                contentColor = Color.White,
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
@Composable
fun MainsScreenPreview() {
    MainScreen(HomeUIState())
}