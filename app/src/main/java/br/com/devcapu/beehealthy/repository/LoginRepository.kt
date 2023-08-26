package br.com.devcapu.beehealthy.repository

import com.google.firebase.auth.FirebaseAuth

class LoginRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val isSignedIn = firebaseAuth.currentUser != null

    fun login(
        email: String,
        password: String,
    ) {
//        if (isSignedIn) return onSuccess()

        val login = firebaseAuth.signInWithEmailAndPassword(email, password)

//        login.addOnSuccessListener { Result.success(it) }
//        login.addOnFailureListener { onFailure(it) }
    }

    fun logout(onSuccess: () -> Unit) {
        firebaseAuth.signOut()
        onSuccess()
    }
}