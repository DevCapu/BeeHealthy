package br.com.devcapu.beehealthy.authentication.login.ui.state

data class LoginUIState(
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