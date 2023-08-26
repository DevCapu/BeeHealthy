package br.com.devcapu.beehealthy.screen.main

private const val ADD_FOOD_SCREEN_ROUTE = "ADD_FOOD_SCREEN_ROUTE"

sealed class MainScreens(val route: String) {
    object AddFoodScreen : MainScreens(ADD_FOOD_SCREEN_ROUTE)
}