package br.com.devcapu.beehealthy.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.common.ui.component.BottomNavItem
import br.com.devcapu.beehealthy.main.ui.state.HomeUIState
import br.com.devcapu.beehealthy.main.ui.screen.BodyScreen
import br.com.devcapu.beehealthy.main.ui.screen.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController, state: HomeUIState) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) { HomeScreen(uiState = state) }
        composable(BottomNavItem.Body.screen_route) { BodyScreen() }
    }
}