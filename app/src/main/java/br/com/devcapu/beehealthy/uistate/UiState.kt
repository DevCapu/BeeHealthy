package br.com.devcapu.beehealthy.uistate

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T? = null) : UiState<T>()
    data class Error(val throwable: Throwable) : UiState<Nothing>()
}