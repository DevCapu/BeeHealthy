package br.com.devcapu.beehealthy.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.component.BottomNavItem
import br.com.devcapu.beehealthy.diary.ui.screen.DiaryScreen
import br.com.devcapu.beehealthy.diary.ui.state.DiaryUiState

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    mainNavController: NavController,
    state: DiaryUiState
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            DiaryScreen(
                uiState = state,
                mainNavController = mainNavController
            )
        }
    }
}