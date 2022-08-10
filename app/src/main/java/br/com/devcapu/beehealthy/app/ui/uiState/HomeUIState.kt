package br.com.devcapu.beehealthy.app.ui.uiState

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.app.ui.theme.Carbohyd
import br.com.devcapu.beehealthy.app.ui.theme.Fats
import br.com.devcapu.beehealthy.app.ui.theme.Protein

data class HomeUIState(
    val name: String = "",
    val caloriesToCommitObjective: Double = 0.0,
    val progressBar: List<ProgressBar> =  listOf(
        ProgressBar(progress = 0f, color = Carbohyd, size = 96.dp),
        ProgressBar(progress = 0f, color = Protein, size = 120.dp),
        ProgressBar(progress = 0f, color = Fats, size = 144.dp)
    ),
    val macros: List<Macro> = listOf(
        Macro(
            color = Carbohyd,
            name = "Carboidrato",
            weight = 32,
            percentage = 0f
        ),
        Macro(
            color = Protein,
            name = "Proteína",
            weight = 21,
            percentage = 0f
        ),
        Macro(
            color = Fats,
            name = "Gordura",
            weight = 56,
            percentage = 0f
        ),
    )
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