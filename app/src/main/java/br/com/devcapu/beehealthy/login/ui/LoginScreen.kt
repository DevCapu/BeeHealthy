package br.com.devcapu.beehealthy.login.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.register.ui.RegisterActivity
import br.com.devcapu.beehealthy.app.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.app.ui.component.OutlineInput
import br.com.devcapu.beehealthy.app.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.app.ui.extension.visualizationMode

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState(initial = LoginUI())
    LoginScreen(state = state)
}

@Composable
private fun LoginScreen(state: LoginUI) = FormWithBeeHealthIdentity {
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualizationMode = VisualTransformation.visualizationMode(showPassword)
    val context = LocalContext.current
    val manager = LocalFocusManager.current

    OutlineInput(
        value = state.email,
        onValueChange = state.onEmailChanged,
        placeholder = { Text(text = stringResource(id = R.string.email_label)) },
        isShowingError = state.showEmailErrorMessage,
        errorMessage = state.emailErrorMessage,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        options = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        actions = KeyboardActions(onNext = { manager.moveFocus(FocusDirection.Next) })
    )

    OutlineInput(
        value = state.password,
        onValueChange = state.onPasswordChanged,
        placeholder = { Text(text = stringResource(id = R.string.password_label)) },
        visualTransformation = passwordVisualizationMode,
        trailingIcon = { PasswordTrailingIcon(showPassword) { showPassword = !showPassword } },
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
        errorMessage = state.passwordErrorMessage,
        isShowingError = state.showPasswordErrorMessage,
        options = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        actions = KeyboardActions(onDone = { manager.clearFocus() })
    )

    Button(
        onClick = state.onClickLogin,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(text = stringResource(R.string.enter_label))
    }

    TextButton(onClick = { goToRegisterActivity(context) }) {
        Text(text = stringResource(R.string.create_an_account))
    }
}

private fun goToRegisterActivity(context: Context) {
    context.startActivity(RegisterActivity.getIntent(context))
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(state = LoginUI())
}