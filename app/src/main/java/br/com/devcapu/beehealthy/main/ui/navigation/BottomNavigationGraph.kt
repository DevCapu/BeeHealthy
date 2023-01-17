package br.com.devcapu.beehealthy.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.common.ui.component.BottomNavItem
import br.com.devcapu.beehealthy.main.ui.screen.BodyScreen
import br.com.devcapu.beehealthy.main.ui.screen.HomeScreen
import br.com.devcapu.beehealthy.main.ui.state.HomeUIState

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    mainNavController: NavController,
    state: HomeUIState
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(
                uiState = state,
                mainNavController = mainNavController
            )
        }
        composable(BottomNavItem.Body.screen_route) { BodyScreen() }
    }
}