package br.com.devcapu.beehealthy.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.devcapu.beehealthy.food.add.AddFoodViewModel
import br.com.devcapu.beehealthy.food.add.screen.AddFoodScreen
import br.com.devcapu.beehealthy.diary.ui.DiaryViewModel
import br.com.devcapu.beehealthy.main.MainScreen
import br.com.devcapu.beehealthy.main.navigation.MainScreens.AddFoodScreen
import br.com.devcapu.beehealthy.main.navigation.MainScreens.HomeScreen

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
        composable(HomeScreen.screen_route) {
            MainScreen(
                viewModel = homeViewModel,
                mainNavController = mainNavController
            )
        }
        composable(
            route = "${AddFoodScreen.screen_route}/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) {
            val mealId = it.arguments?.getString("mealId")!!
            addFoodViewModel.findMeal(mealId)
            AddFoodScreen(
                viewModel = addFoodViewModel,
                mainNavController = mainNavController,
                mealId = mealId
            )
        }
    }
}