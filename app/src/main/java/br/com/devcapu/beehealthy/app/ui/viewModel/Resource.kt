package br.com.devcapu.beehealthy.app.ui.viewModel

data class Resource<T>(val data: T, val errorMessage: String? = null)
