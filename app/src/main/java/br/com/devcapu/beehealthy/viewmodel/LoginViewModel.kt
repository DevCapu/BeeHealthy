package br.com.devcapu.beehealthy.viewmodel

import androidx.lifecycle.ViewModel
import br.com.devcapu.beehealthy.repository.LoginRepository
import br.com.devcapu.beehealthy.uistate.LoginUIState
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    private val loginRepository = LoginRepository()

    val isSignedIn = loginRepository.isSignedIn

    init {
        _uiState.value = LoginUIState(
            onEmailChanged = {
                _uiState.value = _uiState.value.copy(email = it, showEmailErrorMessage = false)
            },
            onPasswordChanged = {
                _uiState.value =
                    _uiState.value.copy(password = it, showPasswordErrorMessage = false)
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
                } else {
                    loginRepository.login(
                        email = _uiState.value.email,
                        password = _uiState.value.password,
                        onSuccess = {
                            _uiState.value = _uiState.value.copy(loggedIn = true)
                        },
                        onFailure = {
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
                    )
                }
            }
        )
    }
}