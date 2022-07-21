package br.com.devcapu.beehealthy.app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.app.ui.component.BottomBar
import br.com.devcapu.beehealthy.app.ui.component.TopBar
import br.com.devcapu.beehealthy.app.ui.navigation.NavigationGraph
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.app.ui.viewModel.HomeViewModel

@Composable
fun MainScreen(viewModel: HomeViewModel, onClickLogout: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { TopBar { onClickLogout() } },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color(0xFF8463E5),
                contentColor = Color.White,
                onClick = { /*TODO*/ }
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
    ) { NavigationGraph(
        navController = navController,
        viewModel = viewModel
    ) }
}