package br.com.devcapu.beehealthy.main.ui.state

data class MacrosUiState(
    val carbohydrateUiState: MacroUiState = MacroUiState(),
    val proteinUiState: MacroUiState = MacroUiState(),
    val fatUiState: MacroUiState = MacroUiState()
)

data class MacroUiState(
    val ingested: String = "0",
    val total: String = "0"
)