package br.com.devcapu.beehealthy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.devcapu.beehealthy.dao.BeeHealthyDatabase
import br.com.devcapu.beehealthy.model.patient.Email
import br.com.devcapu.beehealthy.model.patient.Patient
import br.com.devcapu.beehealthy.repository.HealthRepository
import br.com.devcapu.beehealthy.repository.PatientRepository
import br.com.devcapu.beehealthy.repository.RegisterRepository
import br.com.devcapu.beehealthy.uistate.RegisterUIState
import br.com.devcapu.beehealthy.usecase.SavePatient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel(
    private val patientRepository: PatientRepository,
    private val healthRepository: HealthRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState

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
            )
    }

    fun savePatient() {
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
            password = _uiState.value.password,
            onSuccess = { _uiState.value = uiState.value.copy(finished = true) },
            onFailure = { }
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = (this[APPLICATION_KEY] as Context)
                val database = BeeHealthyDatabase.getInstance(context)
                RegisterViewModel(
                    patientRepository = PatientRepository(database.patientDao()),
                    healthRepository = HealthRepository(database.healthResultDao())
                )
            }
        }
    }
}
