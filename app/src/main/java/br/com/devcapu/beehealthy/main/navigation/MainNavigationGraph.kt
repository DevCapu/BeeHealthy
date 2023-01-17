package br.com.devcapu.beehealthy.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.food.add.AddFoodViewModel
import br.com.devcapu.beehealthy.food.add.screen.AddFoodScreen
import br.com.devcapu.beehealthy.diary.ui.DiaryViewModel
import br.com.devcapu.beehealthy.main.MainScreen

sealed class MainScreens(val screen_route: String) {
    object HomeScreen : MainScreens("home_screen")
    object AddFoodScreen : MainScreens("add_food_screen")
}

@Composable
fun MainNavigationGraph(
    homeViewModel: DiaryViewModel,
    addFoodViewModel: AddFoodViewModel,
) {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = "home_screen") {
        composable(MainScreens.HomeScreen.screen_route) {
            MainScreen(
                viewModel = homeViewModel,
                mainNavController = mainNavController
            )
        }
        composable(MainScreens.AddFoodScreen.screen_route) { AddFoodScreen(addFoodViewModel,
            mainNavController) }
    }
}