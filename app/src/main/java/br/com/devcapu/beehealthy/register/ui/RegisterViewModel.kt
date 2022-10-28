package br.com.devcapu.beehealthy.register.ui

import androidx.lifecycle.*
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.model.patient.Email
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
import br.com.devcapu.beehealthy.domain.useCase.SavePatient
import br.com.devcapu.beehealthy.register.ui.screen.OnboardSteps.*
import br.com.devcapu.beehealthy.register.ui.state.RegisterUIState
import br.com.devcapu.beehealthy.register.ui.state.StepUIState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState

    private var _stepState = MutableStateFlow(StepUIState())
    val stepState: StateFlow<StepUIState> = _stepState

    init {
        _uiState.value =
            RegisterUIState(
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
                onGoToNextStep = { _stepState.value.onGoToNextStep() }
            )

        _stepState.value = _stepState.value.copy(
            onGoToNextStep = {
                when(_stepState.value.step) {
                    AUTHENTICATION_REGISTER -> {
                        _stepState.value = _stepState.value.copy(step = USER_REGISTER_FORM)
                    }
                    USER_REGISTER_FORM -> {
                        _stepState.value = _stepState.value.copy(step = BIOLOGICAL_GENDER_SELECTION)
                    }
                    BIOLOGICAL_GENDER_SELECTION -> {
                        _stepState.value = _stepState.value.copy(step = OBJECTIVE_SELECTION)
                    }
                    OBJECTIVE_SELECTION -> {
                        _stepState.value = _stepState.value.copy(step = ACTIVITY_LEVEL_SELECTION)
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
