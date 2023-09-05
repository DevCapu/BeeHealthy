package br.com.devcapu.auth.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class RegisterRepository {
    private val auth = FirebaseAuth.getInstance()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated =  _isAuthenticated.asSharedFlow()

    suspend fun register(email: String, password: String) {
        val register = auth.createUserWithEmailAndPassword(email, password)
        register.addOnSuccessListener { onSuccess() }
        register.addOnFailureListener { onFailure(it) }

        try {
            val result = auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _isAuthenticated.emit(result.user != null)
                }.addOnFailureListener {
                    _isAuthenticated.emit(false)
                }

        } catch (e: Exception) {
            _isAuthenticated.emit(false)
        }
    }
}