package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.domain.repository.PatientRepository

class HomeViewModel(
    patientRepository: PatientRepository
) : ViewModel() {

    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    fun calculateBMI() {

    }

    class Factory(
        private val patientRepository: PatientRepository
        ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(
                patientRepository = patientRepository
            ) as T
        }
    }
}