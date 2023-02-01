package br.com.devcapu.beehealthy.screen.auth.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.Down
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel
import br.com.devcapu.beehealthy.uistate.RegisterUIState
import br.com.devcapu.beehealthy.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.component.OutlineInput
import br.com.devcapu.beehealthy.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.common.ui.extension.visualizationMode

const val credentialScreenRoute = "credentialsRegistrationScreen"
fun NavGraphBuilder.credentialsScreen(
    onGoToNextScreen: () ->Unit,
    onGoToLogin: () -> Unit
) {
    composable(
        route = credentialScreenRoute
    ) {
        val viewModel: RegisterViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        AuthRegisterScreen(
            state = uiState,
            onEmailChange = uiState.onEmailChanged,
            onPasswordChange = uiState.onPasswordChanged,
            onPasswordConfirmationChange = uiState.onPasswordConfirmationChanged,
            onClickNextStep = onGoToNextScreen,
            goToLogin = onGoToLogin
        )
    }
}

@Composable
fun AuthRegisterScreen(
    state: RegisterUIState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmationChange: (String) -> Unit,
    onClickNextStep: () -> Unit,
    goToLogin: () -> Unit
) = FormWithBeeHealthIdentity {
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualizationMode = VisualTransformation.visualizationMode(showPassword)
    val manager = LocalFocusManager.current
    val modifier = Modifier
        .padding(bottom = 16.dp)
        .fillMaxWidth()

    OutlineInput(
        modifier = modifier,
        value = state.email,
        onValueChange = onEmailChange,
        placeholder = { Text(text = stringResource(R.string.email_label)) },
        options = KeyboardOptions(keyboardType = Email, imeAction = Next),
        actions = KeyboardActions(onNext = { manager.moveFocus(Down) })
    )

    OutlineInput(
        modifier = modifier,
        value = state.password,
        onValueChange = onPasswordChange,
        placeholder = { Text(text = stringResource(R.string.password_label)) },
        visualTransformation = passwordVisualizationMode,
        trailingIcon = { PasswordTrailingIcon(showPassword) { showPassword = !showPassword } },
        options = KeyboardOptions(keyboardType = Email, imeAction = Next),
        actions = KeyboardActions(onNext = { manager.moveFocus(Down) })
    )

    OutlineInput(
        modifier = modifier,
        value = state.passwordConfirmation,
        onValueChange = onPasswordConfirmationChange,
        placeholder = { Text(text = stringResource(R.string.confirm_password_label)) },
        visualTransformation = passwordVisualizationMode,
        options = KeyboardOptions(
            keyboardType = Email, imeAction = ImeAction.Done
        ),
        actions = KeyboardActions(onNext = { manager.clearFocus() })
    )

    Button(
        modifier = modifier,
        onClick = onClickNextStep,
    ) {
        Text(text = stringResource(R.string.next_step))
    }

    TextButton(onClick = goToLogin) {
        Text(text = stringResource(R.string.already_have_an_account))
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    AuthRegisterScreen(
        state = RegisterUIState(),
        onEmailChange = { },
        onPasswordChange = { },
        onPasswordConfirmationChange = { },
        onClickNextStep = { },
        goToLogin = { }
    )
}