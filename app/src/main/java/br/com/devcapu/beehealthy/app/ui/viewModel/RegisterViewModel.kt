package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import br.com.devcapu.beehealthy.app.ui.screen.onboarding.OnboardSteps
import br.com.devcapu.beehealthy.domain.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.model.patient.health.ActivityLevel
import br.com.devcapu.beehealthy.domain.model.patient.Email
import br.com.devcapu.beehealthy.domain.model.patient.health.Objective
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
import br.com.devcapu.beehealthy.domain.useCase.SavePatient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirmation by mutableStateOf("")
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var biologicGender by mutableStateOf(BiologicalGender.MALE)
    var activityLevel by mutableStateOf(ActivityLevel.MODERATE)
    var objective by mutableStateOf(Objective.MAINTAIN)

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
                //TODO: Show error on authenticationScreen
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
            email = Email(email),
            age = age.toInt(),
            weight = weight.toFloat(),
            height = height.toFloat(),
            biologicGender = biologicGender,
            activityLevel = activityLevel,
            objective = objective,
            bodyCaloriesNeeds = null
        )

        val savePatient = SavePatient(
            patientRepository = patientRepository,
            healthRepository = healthRepository
        )

        viewModelScope.launch {
            savePatient(patient = patient)
            _finished.value = true
        }
    }

    fun goTo(step: OnboardSteps) { _step.value = step.name }

    class Factory(
        private val patientRepository: PatientRepository,
        private val healthRepository: HealthRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(
                patientRepository = patientRepository,
                healthRepository = healthRepository
            ) as T
        }
    }
}
