package br.com.devcapu.beehealthy.screen.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.contentColorFor
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.component.TopBar
import br.com.devcapu.beehealthy.graph.BottomNavigationGraph
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.usecase.DiaryUiState
import br.com.devcapu.beehealthy.viewmodel.DiaryViewModel
import br.com.devcapu.beehealthy.viewmodel.DiaryViewModel.Companion.Factory

const val HOME_SCREEN_ROUTE = "HOME_SCREEN_ROUTE"

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.homeScreen(onLogout: () -> Unit) {
    composable(route = HOME_SCREEN_ROUTE) {
        val viewModel: DiaryViewModel = viewModel(
            viewModelStoreOwner = LocalContext.current as ComponentActivity,
            factory = Factory
        )
        val uiState by viewModel.state.collectAsState()

        HomeScreen(state = uiState, onLogout = onLogout)
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    state: DiaryUiState,
    onLogout: () -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = { TopBar { state.onClickLogout { onLogout() } } },
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
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
    ) {
        BottomNavigationGraph(navController = navController)
    }
}

@ExperimentalMaterialApi
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    BeeHealthyTheme {
        HomeScreen(state = DiaryUiState()) { }
    }
}