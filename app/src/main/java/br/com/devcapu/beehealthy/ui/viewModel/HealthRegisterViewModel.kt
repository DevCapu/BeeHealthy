package br.com.devcapu.beehealthy.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.domain.model.Patient
import br.com.devcapu.domain.repository.PatientRepository
import br.com.devcapu.domain.useCase.SavePatient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HealthRegisterViewModel(private val patientDataSource: PatientDataSource): ViewModel() {

    var email by mutableStateOf("")
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var biologicGender by mutableStateOf("")
    var activityLevel by mutableStateOf("")
    var objective by mutableStateOf("")

    fun finishSignUp() {
        val patient = Patient(
            name = name,
            email = email,
            age = age,
            weight = weight.toFloat(),
            height = height.toFloat(),
            biologicGender = biologicGender,
            activityLevel = activityLevel,
            objective = objective
        )

        val patientRepository = PatientRepository(patientDataSource)
        val savePatient = SavePatient(patientRepository = patientRepository)

        CoroutineScope(Dispatchers.IO).launch{
            savePatient(patient = patient)
        }
    }

    class Factory(private val patientDataSource: PatientDataSource): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HealthRegisterViewModel(patientDataSource) as T
        }
    }
}