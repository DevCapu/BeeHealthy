package br.com.devcapu.beehealthy.authentication.register.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.Down
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.authentication.login.ui.LoginActivity
import br.com.devcapu.beehealthy.authentication.register.ui.RegisterViewModel
import br.com.devcapu.beehealthy.authentication.register.ui.state.RegisterUIState
import br.com.devcapu.beehealthy.common.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.common.ui.component.OutlineInput
import br.com.devcapu.beehealthy.common.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.common.ui.extension.visualizationMode

@Composable
fun AuthRegisterScreen(viewModel: RegisterViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    AuthRegisterScreen(state = state)
}

@Composable
fun AuthRegisterScreen(state: RegisterUIState) = FormWithBeeHealthIdentity {
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualizationMode = VisualTransformation.visualizationMode(showPassword)
    val manager = LocalFocusManager.current
    val context = LocalContext.current
    val modifier = Modifier
        .padding(bottom = 16.dp)
        .fillMaxWidth()

    OutlineInput(
        modifier = modifier,
        value = state.email,
        onValueChange = state.onEmailChanged,
        placeholder = { Text(text = stringResource(R.string.email_label)) },
        options = KeyboardOptions(keyboardType = Email, imeAction = Next),
        actions = KeyboardActions(onNext = { manager.moveFocus(Down) })
    )

    OutlineInput(
        modifier = modifier,
        value = state.password,
        onValueChange = state.onPasswordChanged,
        placeholder = { Text(text = stringResource(R.string.password_label)) },
        visualTransformation = passwordVisualizationMode,
        trailingIcon = { PasswordTrailingIcon(showPassword) { showPassword = !showPassword } },
        options = KeyboardOptions(keyboardType = Email, imeAction = Next),
        actions = KeyboardActions(onNext = { manager.moveFocus(Down) })
    )

    OutlineInput(
        modifier = modifier,
        value = state.passwordConfirmation,
        onValueChange = state.onPasswordConfirmationChanged,
        placeholder = { Text(text = stringResource(R.string.confirm_password_label)) },
        visualTransformation = passwordVisualizationMode,
        options = KeyboardOptions(
            keyboardType = Email,
            imeAction = ImeAction.Done
        ),
        actions = KeyboardActions(onNext = { manager.clearFocus() })
    )

    Button(
        modifier = modifier,
        onClick = state.onGoToNextStep,
    ) {
        Text(text = stringResource(R.string.next_step))
    }

    TextButton(onClick = { context.startActivity(LoginActivity.getIntent(context)) }) {
        Text(text = stringResource(R.string.already_have_an_account))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    AuthRegisterScreen(RegisterUIState())
}