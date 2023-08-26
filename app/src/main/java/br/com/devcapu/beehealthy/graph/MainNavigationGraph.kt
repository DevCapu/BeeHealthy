package br.com.devcapu.beehealthy.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import br.com.devcapu.beehealthy.screen.main.HOME_SCREEN_ROUTE
import br.com.devcapu.beehealthy.screen.main.homeScreen

const val MAIN_GRAPH_ROUTE = "MAIN_GRAPH_ROUTE"

fun NavController.navigateToHomeScreen() = navigate(MAIN_GRAPH_ROUTE)

fun NavGraphBuilder.mainNavGraph(
    onLogout: () -> Unit
) {
    navigation(
        route = MAIN_GRAPH_ROUTE,
        startDestination = HOME_SCREEN_ROUTE,
    ) {
        homeScreen(
            onLogout = onLogout
        )
//        composable(
//            route = "${AddFoodScreen.route}/{mealId}",
//            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
//        ) {
//            val mealId = remember { it.arguments?.getString("mealId")!! }
//            LaunchedEffect(key1 = null) { addFoodViewModel.find(mealId.toLong()) }
//
//            AddFoodScreen(
//                viewModel = addFoodViewModel,
//                mainNavController = mainNavController,
//            )
//        }
    }
}