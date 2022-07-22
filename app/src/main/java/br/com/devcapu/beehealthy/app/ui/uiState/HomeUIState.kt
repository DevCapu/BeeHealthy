package br.com.devcapu.beehealthy.app.ui.uiState

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class HomeUIState(
    val name: String = "",
    val caloriesToCommitObjective: Double = 0.0,
    val progressBar: List<ProgressBar> = emptyList(),
    val macros: List<Macro> = emptyList()
)

data class ProgressBar(
    val progress: Float,
    val color: Color,
    val size: Dp,
)

data class Macro(
    val color: Color,
    val name: String,
    val weight: Int,
    val percentage: Float
)