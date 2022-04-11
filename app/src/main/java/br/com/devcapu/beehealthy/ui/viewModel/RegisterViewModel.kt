package br.com.devcapu.beehealthy.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import br.com.devcapu.beehealthy.data.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.ui.screen.onboarding.OnboardSteps
import br.com.devcapu.domain.model.BiologicalGender
import br.com.devcapu.domain.model.Patient
import br.com.devcapu.domain.repository.HealthRepository
import br.com.devcapu.domain.repository.PatientRepository
import br.com.devcapu.domain.useCase.SavePatient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val patientDataSource: PatientDataSource,
    private val healthResultDataSource: HealthResultDataSource,
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirmation by mutableStateOf("")
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var biologicGender by mutableStateOf("MALE")
    var activityLevel by mutableStateOf("")
    var objective by mutableStateOf("")

    private val passwordsAreTheSame: Boolean
        get() = password == passwordConfirmation

    private val _step = MutableLiveData(OnboardSteps.AUTHENTICATION_REGISTER.name)
    val step: LiveData<String> = _step

    private val _finished = MutableLiveData(false)
    val finished: LiveData<Boolean> = _finished

    private val _snackbar = MutableLiveData("")
    val snackbar: LiveData<String> = _snackbar

    fun signUp() {
        verifyPasswordAndPasswordAuthentication()
        savePatientOnFirebase()
    }

    private fun savePatientOnFirebase() {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { savePatient() }
            .addOnFailureListener {
    //             TODO: Show error on authenticationScreen
            }
    }

    private fun verifyPasswordAndPasswordAuthentication() {
        if (!passwordsAreTheSame) {
//          TODO: Show Snackbar and go to authenticationScreen
        }
    }

    private fun savePatient() {
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

        viewModelScope.launch {
            savePatient(patient = patient)
            _finished.value = true
        }
    }

    fun goTo(name: String) {
        _step.value = name
    }

    class Factory(
        private val patientDataSource: PatientDataSource,
        private val healthResultDataSource: HealthResultDataSource,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(patientDataSource, healthResultDataSource) as T
        }
    }
}
