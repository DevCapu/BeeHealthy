package br.com.devcapu.beehealthy.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.domain.repository.PatientRepository

class HomeViewModel(patientDataSource: PatientDataSource) : ViewModel() {
    private val patientRepository = PatientRepository(patientDataSource)

    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    fun calculateBMI() {

    }

    class Factory(private val patientDataSource: PatientDataSource) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(patientDataSource = patientDataSource) as T
        }
    }
}