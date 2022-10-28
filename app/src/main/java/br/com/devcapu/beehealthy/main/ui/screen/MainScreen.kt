package br.com.devcapu.beehealthy.register.ui.screen

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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.common.ui.activity.AddFoodActivity
import br.com.devcapu.beehealthy.common.ui.component.BottomBar
import br.com.devcapu.beehealthy.common.ui.component.TopBar
import br.com.devcapu.beehealthy.main.navigation.NavigationGraph
import br.com.devcapu.beehealthy.main.ui.HomeViewModel

@Composable
fun MainScreen(
    viewModel: HomeViewModel = viewModel(),
    onClickLogout: () -> Unit,
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { TopBar { onClickLogout() } },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color(0xFF8463E5),
                contentColor = Color.White,
                onClick = {
                    context.startActivity(AddFoodActivity.getIntent(context))
                }
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
            viewModel = viewModel
        )
    }
}