package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.component.BottomNavItem
import br.com.devcapu.beehealthy.screen.main.DIARY_SCREEN_ROUTE
import br.com.devcapu.beehealthy.screen.main.DiaryScreen
import br.com.devcapu.beehealthy.screen.main.diaryScreen
import br.com.devcapu.beehealthy.usecase.DiaryUiState

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = DIARY_SCREEN_ROUTE) {
        diaryScreen()
    }
}