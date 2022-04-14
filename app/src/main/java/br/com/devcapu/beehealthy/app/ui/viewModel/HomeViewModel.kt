package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.app.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.app.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.domain.repository.PatientRepository

class HomeViewModel(
    patientDataSource: PatientDataSource,
    healthResultDataSource: HealthResultDataSource
) : ViewModel() {
    private val patientRepository = PatientRepository(patientDataSource)

    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    fun calculateBMI() {

    }

    class Factory(
        private val patientDataSource: PatientDataSource,
        private val healthResultDataSource: HealthResultDataSource
        ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(
                patientDataSource = patientDataSource,
                healthResultDataSource = healthResultDataSource
            ) as T
        }
    }
}