package br.com.devcapu.beehealthy.uistate

data class CaloriesUiState(
    val consumedCalories: String = "0",
    val remainingCalories: String = "0",
    val caloriesToCommitObjective: String = "0",
    val expendedCalories: String = "0",
)