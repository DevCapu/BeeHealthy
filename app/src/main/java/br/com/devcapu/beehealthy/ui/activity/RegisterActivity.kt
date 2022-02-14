package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel

class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goToNextStep.observe(this) {
            if (it) {
                val intent = HealthRegisterActivity.getIntent(this)
                intent.putExtra("PATIENT_EMAIL", viewModel.email)
                startActivity(intent)
            }
        }

        setContent {
            var showPassword by remember { mutableStateOf(false) }
            val passwordVisualizationMode = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }

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
                onClickRegisterButton = { viewModel.register() },
                onClickAlreadyHasAnAccount = { goToLoginActivity() }
            )
        }
    }

    private fun goToLoginActivity() {
        startActivity(LoginActivity.getIntent(this))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }
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
) {
    FormWithBeeHealthIdentity {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = { Text(text = "Email") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange ,
            placeholder = { Text(text = "Senha") },
            visualTransformation = passwordVisualizationMode,
            trailingIcon = {
                PasswordTrailingIcon(showPassword = showPassword) { onChangePasswordVisualizationMode() }
            },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = onPasswordConfirmationChange,
            placeholder = { Text(text = "Confirmar senha") },
            visualTransformation = passwordVisualizationMode,
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
        )

        Button(
            onClick = onClickRegisterButton,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) { Text(text = "Entrar") }

        TextButton(onClick = onClickAlreadyHasAnAccount) { Text(text = "Já tenho uma conta") }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterContent(
        email = "",
        password =  "",
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