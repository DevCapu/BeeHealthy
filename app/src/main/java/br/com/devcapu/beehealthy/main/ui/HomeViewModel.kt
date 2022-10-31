package br.com.devcapu.beehealthy.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.login.data.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    private val loginRepository = LoginRepository()
    private val firebaseAuth = FirebaseAuth.getInstance()

    private var _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state

    init {
        firebaseAuth.currentUser?.email?.let {
            viewModelScope.launch {
                val patient = patientRepository.searchUserWith(email = it)
                patient.bodyCaloriesNeeds?.let {
                    _state.value = HomeUIState(
                        name = patient.name,
                        caloriesToCommitObjective = it.caloriesToCommitObjective.value,
                        onClickLogout = { loginRepository.logout { it() } }
                    )
                }
            }
        }
    }

    class Factory(
        private val patientRepository: PatientRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(patientRepository = patientRepository) as T
        }
    }
}