package br.com.devcapu.beehealthy.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.common.domain.model.Patient
import br.com.devcapu.beehealthy.common.domain.model.patient.Email
import br.com.devcapu.beehealthy.common.data.repository.HealthRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.register.domain.useCase.SavePatient
import br.com.devcapu.beehealthy.register.data.RegisterRepository
import br.com.devcapu.beehealthy.register.ui.screen.OnboardSteps.*
import br.com.devcapu.beehealthy.register.ui.state.RegisterUIState
import br.com.devcapu.beehealthy.register.ui.state.StepUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState

    private var _stepState = MutableStateFlow(StepUIState())
    val stepState: StateFlow<StepUIState> = _stepState

    private val registerRepository = RegisterRepository()

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
                when (_stepState.value.step) {
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
                    ACTIVITY_LEVEL_SELECTION -> savePatient()
                }
            }
        )
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
            healthRepository = healthRepository,
            registerRepository = registerRepository
        )

        savePatient(
            patient = patient,
            onSuccess = { _uiState.value = uiState.value.copy(finished = true) },
            onFailure = { }
        )
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
