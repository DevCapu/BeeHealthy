package br.com.devcapu.beehealthy.app.ui.uiState

import br.com.devcapu.beehealthy.app.ui.component.ProgressBar

data class HomeUIState(
    val name: String = "",
    val caloriesToCommitObjective: Double = 0.0,
    val progressBar: List<ProgressBar> = emptyList(),
)