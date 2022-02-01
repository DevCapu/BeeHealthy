package br.com.devcapu.beehealthy.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.domain.model.Patient
import br.com.devcapu.domain.repository.PatientRepository
import br.com.devcapu.domain.useCase.CalculateBMI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(patientDataSource: PatientDataSource) : ViewModel() {
    private val patientRepository = PatientRepository(patientDataSource)

    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    fun calculateBMI() {
        val patient = Patient(height = height.toFloat(), weight = weight.toFloat())
        val calculateBMI = CalculateBMI()
        val bmi = calculateBMI(patient = patient)
        CoroutineScope(Dispatchers.IO).launch {
            patientRepository.saveBMI(bmi = bmi)
        }
    }

    class Factory(private val patientDataSource: PatientDataSource) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(patientDataSource = patientDataSource) as T
        }
    }
}