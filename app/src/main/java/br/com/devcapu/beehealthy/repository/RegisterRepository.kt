package br.com.devcapu.beehealthy.repository

import com.google.firebase.auth.FirebaseAuth

class RegisterRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun register(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        val register = firebaseAuth.createUserWithEmailAndPassword(email, password)
        register.addOnSuccessListener { onSuccess() }
        register.addOnFailureListener { onFailure(it) }
    }
}