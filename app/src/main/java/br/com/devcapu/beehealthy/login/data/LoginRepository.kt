package br.com.devcapu.beehealthy.login.data

import com.google.firebase.auth.FirebaseAuth

class LoginRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    val isSignedIn = firebaseAuth.currentUser != null

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        if (isSignedIn) return onSuccess()

        val login = firebaseAuth.signInWithEmailAndPassword(email, password)

        login.addOnSuccessListener { onSuccess() }
        login.addOnFailureListener { onFailure(it) }
    }
}