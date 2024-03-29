package br.com.devcapu.beehealthy.screen.auth.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.common.ui.extension.visualizationMode
import br.com.devcapu.beehealthy.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.component.OutlineInput
import br.com.devcapu.beehealthy.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.screen.LoadingScreen
import br.com.devcapu.beehealthy.uistate.LoginUIState
import br.com.devcapu.beehealthy.uistate.UiState.Error
import br.com.devcapu.beehealthy.uistate.UiState.Initial
import br.com.devcapu.beehealthy.uistate.UiState.Loading
import br.com.devcapu.beehealthy.uistate.UiState.Success
import br.com.devcapu.beehealthy.viewmodel.LoginViewModel

const val LOGIN_ROUTE = "LOGIN_ROUTE"

fun NavController.navigateToLogin() = navigate(LOGIN_ROUTE)

fun NavGraphBuilder.loginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) {
    composable(LOGIN_ROUTE) {
        val viewModel: LoginViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        val formState by viewModel.formState.collectAsState()

        when (uiState) {
            is Success -> { onNavigateToHome() }
            Initial, is Error -> {
                LoginScreen(
                    formState = formState,
                    onGoToRegisterScreen = onNavigateToRegisterScreen
                )
            }
            Loading -> { LoadingScreen() }
        }
    }
}

@Composable
fun LoginScreen(
    formState: LoginUIState,
    onGoToRegisterScreen: () -> Unit
) {
    FormWithBeeHealthIdentity {
        var showPassword by remember { mutableStateOf(false) }
        val passwordVisualizationMode = VisualTransformation.visualizationMode(showPassword)
        val manager = LocalFocusManager.current

        OutlineInput(
            value = formState.email,
            onValueChange = formState.onEmailChanged,
            label = { Text(text = stringResource(id = R.string.email_label)) },
            isShowingError = formState.showEmailErrorMessage,
            errorMessage = formState.emailErrorMessage,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            options = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            actions = KeyboardActions(onNext = { manager.moveFocus(FocusDirection.Next) }),
        )

        OutlineInput(
            value = formState.password,
            onValueChange = formState.onPasswordChanged,
            label = { Text(text = stringResource(id = R.string.password_label)) },
            visualTransformation = passwordVisualizationMode,
            trailingIcon = {
                PasswordTrailingIcon(showPassword) {
                    showPassword = !showPassword
                }
            },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            errorMessage = formState.passwordErrorMessage,
            isShowingError = formState.showPasswordErrorMessage,
            options = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            actions = KeyboardActions(onDone = { manager.clearFocus() })
        )

        Button(
            onClick = formState.onClickLogin,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = stringResource(R.string.enter_label))
        }

        TextButton(onClick = onGoToRegisterScreen) {
            Text(text = stringResource(R.string.create_an_account))
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
//    LoginScreen(
//        state = LoginUIState(),
//        onGoToRegisterScreen = { }
//    )
}