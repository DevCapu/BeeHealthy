package br.com.devcapu.beehealthy.main.ui.state

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.devcapu.beehealthy.common.data.datasource.LocalCategoryDataSource

data class HomeUIState(
    val caloriesUiState: CaloriesUiState = CaloriesUiState(),
    val macrosUiState: MacrosUiState = MacrosUiState(),
    val mealsUiState: MealsUiState = MealsUiState(),
    val categories: List<LocalCategoryDataSource.Category> = emptyList(),
    val onClickLogout: (() -> Unit) -> Unit = { },
    val onClickFAB: () -> Unit = { },
)

data class ProgressBar(
    val progress: Float,
    val color: Color,
)

data class UIMacro(
    val color: Color,
    @StringRes val name: Int,
    val weight: Int,
    val percentage: Float,
)