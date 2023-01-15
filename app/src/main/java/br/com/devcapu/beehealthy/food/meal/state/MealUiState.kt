package br.com.devcapu.beehealthy.food.meal.state

data class MealsUiState(
    val breakfastUiState: MealUiState = MealUiState(),
    val lunchUiState: MealUiState = MealUiState(),
    val dinnerUiState: MealUiState = MealUiState(),
    val snackUiState: MealUiState = MealUiState(),
)

data class MealUiState(
    val ingested: String = "0",
    val total: String = "0",
)