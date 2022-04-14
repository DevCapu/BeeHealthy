package br.com.devcapu.beehealthy.app.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    var email by mutableStateOf(Resource(""))
    var password by mutableStateOf(Resource(""))

    private val _loggedIn = MutableLiveData<Boolean>(false)
    var signedIn: LiveData<Boolean> = _loggedIn
        private set

    fun isSignedIn(): Boolean {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser != null
    }

    fun signIn() {
        if (email.data.isEmpty()) {
            email = Resource(email.data, "Email está vazio")
            return
        } else if (password.data.isEmpty()) {
            password = Resource(password.data, "Senha está vazia")
            return
        }

        val auth = Firebase.auth
        if (!isSignedIn()) {
            val createUserWithEmailAndPassword =
                auth.signInWithEmailAndPassword(email.data, password.data)
            createUserWithEmailAndPassword.addOnSuccessListener {
                _loggedIn.value = true
            }
            createUserWithEmailAndPassword.addOnFailureListener {

            }
        }
    }
}