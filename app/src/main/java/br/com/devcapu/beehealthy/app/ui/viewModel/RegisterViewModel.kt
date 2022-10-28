package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.lifecycle.*
import br.com.devcapu.beehealthy.app.ui.screen.onboarding.OnboardSteps
import br.com.devcapu.beehealthy.app.ui.screen.onboarding.OnboardSteps.*
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.model.patient.Email
import br.com.devcapu.beehealthy.domain.model.patient.health.ActivityLevel
import br.com.devcapu.beehealthy.domain.model.patient.health.BiologicalGender
import br.com.devcapu.beehealthy.domain.model.patient.health.Objective
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
import br.com.devcapu.beehealthy.domain.useCase.SavePatient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterUI(
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val name: String = "",
    val birthday: String = "",
    val weight: String = "",
    val height: String = "",
    val biologicalGender: BiologicalGender = BiologicalGender.MALE,
    val activityLevel: ActivityLevel = ActivityLevel.LOW,
    val objective: Objective = Objective.LOSE,
    val onEmailChanged: (String) -> Unit = { },
    val onPasswordChanged: (String) -> Unit = { },
    val onPasswordConfirmationChanged: (String) -> Unit = { },
    val onNameChanged: (String) -> Unit = { },
    val onBirthdayChanged: (String) -> Unit = { },
    val onWeightChanged: (String) -> Unit = { },
    val onHeightChanged: (String) -> Unit = { },
    val onBiologicalGenderChanged: (BiologicalGender) -> Unit = { },
    val onActivityLevelChanged: (ActivityLevel) -> Unit = { },
    val onObjectiveChanged: (Objective) -> Unit = { },
    val step: OnboardSteps = AUTHENTICATION_REGISTER,
    val onGoToNextStep: () -> Unit = { },
    val finished: Boolean = false
) {
    val passwordsAreTheSame = password == passwordConfirmation
}

class RegisterViewModel(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(RegisterUI())
    val uiState: StateFlow<RegisterUI> = _uiState

    init {
        _uiState.value =
            RegisterUI(
                onEmailChanged = {
                    _uiState.value = _uiState.value.copy(email = it)
                },
                onPasswordChanged = {
                    _uiState.value = _uiState.value.copy(password = it)
                },
                onPasswordConfirmationChanged = {
                    _uiState.value = _uiState.value.copy(passwordConfirmation = it)
                },
                onNameChanged = {
                    _uiState.value = _uiState.value.copy(name = it)
                },
                onBirthdayChanged = {
                    _uiState.value = _uiState.value.copy(birthday = it)
                },
                onWeightChanged = {
                    _uiState.value = _uiState.value.copy(weight = it)
                },
                onHeightChanged = {
                    _uiState.value = _uiState.value.copy(height = it)
                },
                onBiologicalGenderChanged = {
                    _uiState.value = _uiState.value.copy(biologicalGender = it)
                },
                onActivityLevelChanged = {
                    _uiState.value = _uiState.value.copy(activityLevel = it)
                },
                onObjectiveChanged = {
                    _uiState.value = _uiState.value.copy(objective = it)
                },
                onGoToNextStep = {
                    when(_uiState.value.step) {
                        AUTHENTICATION_REGISTER -> {
                            _uiState.value = _uiState.value.copy(step = USER_REGISTER_FORM)
                        }
                        USER_REGISTER_FORM -> {
                            _uiState.value = _uiState.value.copy(step = BIOLOGICAL_GENDER_SELECTION)
                        }
                        BIOLOGICAL_GENDER_SELECTION -> {
                            _uiState.value = _uiState.value.copy(step = OBJECTIVE_SELECTION)
                        }
                        OBJECTIVE_SELECTION -> {
                            _uiState.value = _uiState.value.copy(step = ACTIVITY_LEVEL_SELECTION)
                        }
                        ACTIVITY_LEVEL_SELECTION -> {
                            _uiState.value = _uiState.value.copy(finished = true)
                        }
                    }
                }
            )
    }

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
        auth.createUserWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
            .addOnSuccessListener { savePatient() }
            .addOnFailureListener {
                //TODO: Show error on authenticationScreen
            }
    }

    private fun verifyPasswordAndPasswordAuthentication() {
//        if (!_uiState.value.passwordsAreTheSame) {
////          TODO: Show Snackbar and go to authenticationScreen
//        }
    }

    private fun savePatient() {

        val patient = Patient(
            name = _uiState.value.name,
            email = Email(_uiState.value.email),
            age = _uiState.value.birthday.toInt(),
            weight = _uiState.value.weight.toFloat(),
            height = _uiState.value.height.toFloat(),
            biologicGender = _uiState.value.biologicalGender,
            activityLevel = _uiState.value.activityLevel,
            objective = _uiState.value.objective,
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
