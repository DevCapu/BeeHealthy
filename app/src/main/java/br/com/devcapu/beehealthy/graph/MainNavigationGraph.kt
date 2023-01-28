package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.devcapu.beehealthy.viewmodel.DiaryViewModel
import br.com.devcapu.beehealthy.viewmodel.AddFoodViewModel
import br.com.devcapu.beehealthy.food.add.screen.AddFoodScreen
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
        composable(
            route = "${MainScreens.AddFoodScreen.screen_route}/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) {
            val mealId = remember { it.arguments?.getString("mealId")!! }
            LaunchedEffect(key1 = null){ addFoodViewModel.find(mealId.toLong()) }

            AddFoodScreen(
                viewModel = addFoodViewModel,
                mainNavController = mainNavController,
            )
        }
    }
}