package br.com.devcapu.beehealthy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devcapu.beehealthy.uistate.LoginUIState
import br.com.devcapu.beehealthy.uistate.UiState
import br.com.devcapu.beehealthy.uistate.UiState.Initial
import br.com.devcapu.beehealthy.uistate.UiState.Success
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _uiState = MutableStateFlow<UiState<Boolean>>(Initial)
    val uiState = _uiState.asStateFlow()

    private var _formState = MutableStateFlow(LoginUIState())
    val formState = _formState.asStateFlow()

    init {
        _formState.value = LoginUIState(
            onEmailChanged = {
                _formState.value = _formState.value.copy(email = it, showEmailErrorMessage = false)
            },
            onPasswordChanged = {
                _formState.value =
                    _formState.value.copy(password = it, showPasswordErrorMessage = false)
            },
            onClickLogin = {
                if (_formState.value.email.isEmpty()) {
                    _formState.value = _formState.value.copy(
                        emailErrorMessage = "Email vazio",
                        showEmailErrorMessage = true
                    )
                } else if (_formState.value.password.isEmpty()) {
                    _formState.value = _formState.value.copy(
                        passwordErrorMessage = "Password vazio",
                        showPasswordErrorMessage = true
                    )
                } else {
                    viewModelScope.launch {
                        login()
                    }
                }
            }
        )
    }

    private suspend fun login() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)

            val auth = FirebaseAuth.getInstance()
            val loginTask = auth.signInWithEmailAndPassword(
                _formState.value.email,
                _formState.value.password
            )
            loginTask.addOnSuccessListener { _uiState.value = Success() }
            loginTask.addOnFailureListener { handleError(it) }
        }
    }

    private fun handleError(it: Exception) {
        when (it) {
            is FirebaseAuthInvalidCredentialsException -> {
                _formState.value = _formState.value.copy(
                    passwordErrorMessage = "Senha inválida",
                    showPasswordErrorMessage = true
                )
            }

            is FirebaseAuthInvalidUserException -> {
                _formState.value = _formState.value.copy(
                    emailErrorMessage = "Email ou usuário não existe",
                    showEmailErrorMessage = true
                )
            }
        }
        _uiState.value = Initial
    }
}