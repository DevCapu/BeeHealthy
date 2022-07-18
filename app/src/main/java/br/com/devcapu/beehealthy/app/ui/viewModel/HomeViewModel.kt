package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.devcapu.beehealthy.domain.repository.PatientRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class HomeViewModel(
    patientRepository: PatientRepository
) : ViewModel() {

    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    fun calculateBMI() {

    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    class Factory(
        private val patientRepository: PatientRepository
        ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(
                patientRepository = patientRepository
            ) as T
        }
    }
}