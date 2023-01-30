package br.com.devcapu.beehealthy.uistate

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: AddFoodUiState) : UiState()
    data class Error(val message: String?) : UiState()
}