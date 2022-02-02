package br.com.devcapu.beehealthy.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel() : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loggedIn = MutableLiveData<Boolean>(false)
    var signedIn: LiveData<Boolean> = _loggedIn
        private set

    fun isSignedIn(): Boolean {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser != null
    }

    fun signIn() {
        val auth = Firebase.auth
        if (!isSignedIn()) {
            val createUserWithEmailAndPassword = auth.signInWithEmailAndPassword(email, password)
            createUserWithEmailAndPassword.addOnSuccessListener {
                _loggedIn.value = true
            }
            createUserWithEmailAndPassword.addOnFailureListener {
                Log.e("LOGIN", "signIn: ${it.message}", )
            }
        }
    }
}