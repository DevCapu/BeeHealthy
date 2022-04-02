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
import br.com.devcapu.beehealthy.ui.screen.onboarding.OnboardSteps

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

    private val passwordsAreTheSame = password == passwordConfirmation

    private val _step = MutableLiveData(OnboardSteps.OBJECTIVE_SELECTION.name)
    val step: LiveData<String> = _step

//    fun initSignUp(): LiveData<Boolean> {
//        val auth = Firebase.auth
//        if (passwordsAreTheSame) {
//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnSuccessListener {
//                    _userCreated.value = true
//                }.addOnFailureListener {
//                    _userCreated.value = false
//                }
//        }
//        return userCreated
//    }
//
//    fun finishSignUp() {
//        val patient = Patient(
//            name = name,
//            email = email,
//            age = age.toInt(),
//            weight = weight.toFloat(),
//            height = height.toFloat(),
//            biologicGender = BiologicalGender.valueOf(biologicGender.uppercase()),
//            activityLevel = activityLevel,
//            objective = objective
//        )
//
//        val patientRepository = PatientRepository(patientDataSource)
//        val healthRepository = HealthRepository(healthResultDataSource)
//        val savePatient = SavePatient(
//            patientRepository = patientRepository,
//            healthRepository = healthRepository
//        )
//
//        viewModelScope.launch(Dispatchers.IO) {
//            savePatient(patient = patient)
//        }
//        _userCreated.value = true
//    }

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
