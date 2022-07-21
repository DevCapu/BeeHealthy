package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.app.ui.uiState.HomeUIState
import br.com.devcapu.beehealthy.domain.model.Patient
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomeViewModel(
    patientRepository: PatientRepository,
) : ViewModel() {

    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    var homeUIState by mutableStateOf(HomeUIState())

    init {
        val instance = FirebaseAuth.getInstance()
        instance.currentUser?.email?.let {
            viewModelScope.launch {
                val patient = patientRepository.searchUserWith(email = it)
                val bodyCaloriesNeeds = patient.bodyCaloriesNeeds
                bodyCaloriesNeeds.let {
                    if (bodyCaloriesNeeds != null) {
                        homeUIState = HomeUIState(
                            name = patient.name,
                            caloriesToCommitObjective = bodyCaloriesNeeds.caloriesToCommitObjective.value
                        )
                    }
                }
            }
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    class Factory(
        private val patientRepository: PatientRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(patientRepository = patientRepository, ) as T
        }
    }
}