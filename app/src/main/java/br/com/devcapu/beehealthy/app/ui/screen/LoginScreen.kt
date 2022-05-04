package br.com.devcapu.beehealthy.app.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.app.ui.extension.visualizationMode
import br.com.devcapu.beehealthy.app.ui.activity.RegisterActivity
import br.com.devcapu.beehealthy.app.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.app.ui.component.OutlineInput
import br.com.devcapu.beehealthy.app.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.app.ui.viewModel.LoginViewModel
import br.com.devcapu.beehealthy.app.ui.viewModel.Resource

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualizationMode = VisualTransformation.visualizationMode(showPassword)
    val context = LocalContext.current

    LoginContent(
        email = viewModel.email,
        onEmailChange = { viewModel.email = Resource(it) },
        password = viewModel.password,
        onPasswordChange = { viewModel.password = Resource(it) },
        showPassword = showPassword,
        passwordVisualizationMode = passwordVisualizationMode,
        onChangePasswordVisualizationMode = { showPassword = !showPassword },
        onClickOnSignIn = { viewModel.signIn() },
        onClickGoToRegistration = { goToRegisterActivity(context) }
    )
}

private fun goToRegisterActivity(context: Context) {
    context.startActivity(RegisterActivity.getIntent(context))
}

@Composable
private fun LoginContent(
    email: Resource<String>,
    onEmailChange: (String) -> Unit,
    password: Resource<String>,
    showPassword: Boolean,
    passwordVisualizationMode: VisualTransformation,
    onChangePasswordVisualizationMode: () -> Unit,
    onClickGoToRegistration: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickOnSignIn: () -> Unit,
) = FormWithBeeHealthIdentity {
    email.errorMessage.isNullOrEmpty()
    OutlineInput(
        value = email.data,
        onValueChange = onEmailChange,
        placeholder = { Text(text = stringResource(id = R.string.email_label)) },
        isShowingError = !email.errorMessage.isNullOrEmpty(),
        errorMessage = email.data,
        modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
    )

    OutlineInput(
        value = password.data,
        onValueChange = onPasswordChange,
        placeholder = { Text(text = stringResource(id = R.string.password_label)) },
        visualTransformation = passwordVisualizationMode,
        trailingIcon = { PasswordTrailingIcon(showPassword) { onChangePasswordVisualizationMode() } },
        modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
    )

    Button(
        onClick = onClickOnSignIn,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) { Text(text = stringResource(R.string.enter_label)) }

    TextButton(
        onClick = onClickGoToRegistration
    ) { Text(text = stringResource(R.string.create_an_account)) }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginContent(
        email = Resource(""),
        password = Resource(""),
        onPasswordChange = {},
        passwordVisualizationMode = PasswordVisualTransformation(),
        onChangePasswordVisualizationMode = {},
        onClickGoToRegistration = {},
        onClickOnSignIn = {},
        onEmailChange = {},
        showPassword = false
    )
}