package br.com.devcapu.beehealthy.app.ui.screen.onboarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.app.ui.extension.visualizationMode
import br.com.devcapu.beehealthy.app.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.app.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.app.ui.viewModel.RegisterViewModel

@Composable
fun AuthenticationLoginScreen(viewModel: RegisterViewModel) {
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualizationMode = VisualTransformation.visualizationMode(showPassword)

    RegisterContent(
        email = viewModel.email,
        onEmailChange = { viewModel.email = it },
        password = viewModel.password,
        onPasswordChange = { viewModel.password = it },
        passwordConfirmation = viewModel.passwordConfirmation,
        onPasswordConfirmationChange = { viewModel.passwordConfirmation = it },
        showPassword = showPassword,
        passwordVisualizationMode = passwordVisualizationMode,
        onChangePasswordVisualizationMode = { showPassword = !showPassword },
        onClickRegisterButton = { viewModel.goTo(OnboardSteps.USER_REGISTER_FORM.name) },
        onClickAlreadyHasAnAccount = { }
    )
}


@Composable
fun RegisterContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisualizationMode: VisualTransformation,
    onChangePasswordVisualizationMode: () -> Unit,
    showPassword: Boolean,
    passwordConfirmation: String,
    onPasswordConfirmationChange: (String) -> Unit,
    onClickRegisterButton: () -> Unit,
    onClickAlreadyHasAnAccount: () -> Unit,
) = FormWithBeeHealthIdentity {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = { Text(text = stringResource(R.string.email_label)) },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    )

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = { Text(text = stringResource(R.string.password_label)) },
        visualTransformation = passwordVisualizationMode,
        trailingIcon = {
            PasswordTrailingIcon(showPassword = showPassword) { onChangePasswordVisualizationMode() }
        },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    )

    OutlinedTextField(
        value = passwordConfirmation,
        onValueChange = onPasswordConfirmationChange,
        placeholder = { Text(text = stringResource(R.string.confirm_password_label)) },
        visualTransformation = passwordVisualizationMode,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
    )

    Button(
        onClick = onClickRegisterButton,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) { Text(text = stringResource(R.string.next_step)) }

    TextButton(onClick = onClickAlreadyHasAnAccount) {
        Text(text = stringResource(R.string.already_have_an_account))
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterContent(
        email = "",
        password = "",
        passwordConfirmation = "",
        onEmailChange = {},
        onPasswordChange = {},
        onChangePasswordVisualizationMode = {},
        onClickAlreadyHasAnAccount = {},
        onClickRegisterButton = {},
        onPasswordConfirmationChange = {},
        passwordVisualizationMode = PasswordVisualTransformation(),
        showPassword = true
    )
}