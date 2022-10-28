package br.com.devcapu.beehealthy.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.common.ui.component.BottomNavItem
import br.com.devcapu.beehealthy.register.ui.screen.BodyScreen
import br.com.devcapu.beehealthy.register.ui.screen.HomeScreen
import br.com.devcapu.beehealthy.main.ui.HomeViewModel

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: HomeViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) { HomeScreen(uiState = viewModel.homeUIState) }
        composable(BottomNavItem.Body.screen_route) { BodyScreen() }
    }
}