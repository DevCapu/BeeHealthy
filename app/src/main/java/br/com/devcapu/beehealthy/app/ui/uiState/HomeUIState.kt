package br.com.devcapu.beehealthy.app.ui.uiState

data class HomeUIState(
    val name: String = "",
    val caloriesToCommitObjective: Double = 0.0,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
)