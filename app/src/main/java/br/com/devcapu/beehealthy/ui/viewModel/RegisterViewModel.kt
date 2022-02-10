package br.com.devcapu.beehealthy.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirmation by mutableStateOf("")

    private val passwordsAreTheSame
        get() = password == passwordConfirmation

    private val _goToNextStep = MutableLiveData(false)
    val goToNextStep: LiveData<Boolean> = _goToNextStep

    fun register() {
        val auth = Firebase.auth
        if (passwordsAreTheSame) {
            val createUserWithEmailAndPassword = auth.createUserWithEmailAndPassword(
                email, password
            )
            createUserWithEmailAndPassword.addOnSuccessListener {
                _goToNextStep.value = true
            }

            createUserWithEmailAndPassword.addOnFailureListener {
                Log.e("RegisterViewModel",
                    "Register: ${it.message}" +
                        "\n email: $email" +
                        "\n password: $password")
            }
        }
    }
}
