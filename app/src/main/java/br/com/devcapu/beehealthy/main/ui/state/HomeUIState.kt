package br.com.devcapu.beehealthy.main.ui

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.common.ui.theme.Carbohyd
import br.com.devcapu.beehealthy.common.ui.theme.Fats
import br.com.devcapu.beehealthy.common.ui.theme.Protein
import br.com.devcapu.beehealthy.common.domain.model.Macro.*

data class HomeUIState(
    val name: String = "",
    val caloriesToCommitObjective: Double = 0.0,
    val progressBar: List<ProgressBar> =  listOf(
        ProgressBar(progress = 0f, color = Carbohyd, size = 96.dp),
        ProgressBar(progress = 0f, color = Protein, size = 120.dp),
        ProgressBar(progress = 0f, color = Fats, size = 144.dp)
    ),
    val macros: List<UIMacro> = listOf(
        UIMacro(
            color = Carbohyd,
            name = R.string.carbohyd_label,
            weight = CARBOHYD.totalToPercentage(caloriesToCommitObjective),
            percentage = 0.50f
        ),
        UIMacro(
            color = Protein,
            name = R.string.protein_label,
            weight = PROTEIN.totalToPercentage(caloriesToCommitObjective),
            percentage = 0.25f
        ),
        UIMacro(
            color = Fats,
            name = R.string.fat_label,
            weight = FAT.totalToPercentage(caloriesToCommitObjective),
            percentage = 0.25f
        )
    ),
    val onClickLogout: (() -> Unit) -> Unit = { },
    val onClickFAB: () -> Unit = { }
)

data class ProgressBar(
    val progress: Float,
    val color: Color,
    val size: Dp,
)

data class UIMacro(
    val color: Color,
    @StringRes val name: Int,
    val weight: Int,
    val percentage: Float
)