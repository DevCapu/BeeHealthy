package br.com.devcapu.beehealthy.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.data.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.domain.model.BiologicalGender
import br.com.devcapu.domain.model.Patient
import br.com.devcapu.domain.repository.HealthRepository
import br.com.devcapu.domain.repository.PatientRepository
import br.com.devcapu.domain.useCase.SavePatient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HealthRegisterViewModel(
    private val patientDataSource: PatientDataSource,
    private val healthResultDataSource: HealthResultDataSource,
) : ViewModel() {

    var email by mutableStateOf("")
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var biologicGender by mutableStateOf("MALE")
    var activityLevel by mutableStateOf("")
    var objective by mutableStateOf("")

    private val _userCreated = MutableLiveData(false)
    val userCreated: LiveData<Boolean>
        get() = _userCreated

    fun finishSignUp() {
        val patient = Patient(
            name = name,
            email = email,
            age = age.toInt(),
            weight = weight.toFloat(),
            height = height.toFloat(),
            biologicGender = BiologicalGender.valueOf(biologicGender.uppercase()),
            activityLevel = activityLevel,
            objective = objective
        )

        val patientRepository = PatientRepository(patientDataSource)
        val healthRepository = HealthRepository(healthResultDataSource)
        val savePatient = SavePatient(
            patientRepository = patientRepository,
            healthRepository = healthRepository
        )

        CoroutineScope(Dispatchers.IO).launch {
            savePatient(patient = patient)
            _userCreated.value = true
        }
    }

    class Factory(
        private val patientDataSource: PatientDataSource,
        private val healthResultDataSource: HealthResultDataSource,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HealthRegisterViewModel(patientDataSource, healthResultDataSource) as T
        }
    }
}