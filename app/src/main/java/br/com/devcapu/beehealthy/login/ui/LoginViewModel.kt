package br.com.devcapu.beehealthy.login.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LoginUI(
    val email: String = "",
    val password: String = "",
    val onEmailChanged: (String) -> Unit = { },
    val onPasswordChanged: (String) -> Unit = { },
    val onClickLogin: () -> Unit = { },
    val emailErrorMessage: String = "",
    val showEmailErrorMessage: Boolean = false,
    val passwordErrorMessage: String = "",
    val showPasswordErrorMessage: Boolean = false,
    val loggedIn: Boolean = false,
)

class LoginViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(LoginUI())
    val uiState: StateFlow<LoginUI> = _uiState

    init {
        _uiState.value = LoginUI(
            onEmailChanged = {
                _uiState.value = _uiState.value.copy(
                    email = it,
                    showEmailErrorMessage = false
                )
            },
            onPasswordChanged = {
                _uiState.value = _uiState.value.copy(
                    password = it,
                    showPasswordErrorMessage = false
                )
            },
            onClickLogin = {
                if (_uiState.value.email.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        emailErrorMessage = "Email vazio",
                        showEmailErrorMessage = true
                    )
                } else if (_uiState.value.password.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        passwordErrorMessage = "Password vazio",
                        showPasswordErrorMessage = true
                    )
                }

                val auth = Firebase.auth
                if (!isSignedIn() && authenticationValuesAreNotEmpty()) {
                    val createUserWithEmailAndPassword =
                        auth.signInWithEmailAndPassword(
                            _uiState.value.email,
                            _uiState.value.password
                        )
                    createUserWithEmailAndPassword.addOnSuccessListener {
                        _uiState.value = _uiState.value.copy(loggedIn = true)
                    }
                    createUserWithEmailAndPassword.addOnFailureListener {
                        when (it) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                _uiState.value = _uiState.value.copy(
                                    passwordErrorMessage = "Senha inválida",
                                    showPasswordErrorMessage = true
                                )
                            }
                            is FirebaseAuthInvalidUserException -> {
                                _uiState.value = _uiState.value.copy(
                                    emailErrorMessage = "Email ou usuário não existe",
                                    showEmailErrorMessage = true
                                )
                            }
                        }
                    }
                }
            }
        )
    }

    fun isSignedIn(): Boolean {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser != null
    }

    private fun authenticationValuesAreNotEmpty(): Boolean {
        val value = _uiState.value
        return value.email.isNotEmpty() && value.password.isNotEmpty()
    }
}