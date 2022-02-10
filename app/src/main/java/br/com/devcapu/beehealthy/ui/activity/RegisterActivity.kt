package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel

class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goToNextStep.observe(this) {
            if (it) {
                startActivity(HealthRegisterActivity.getIntent(this))
            }
        }

        setContent { RegisterScreen() { goToLoginActivity() } }
    }

    private fun goToLoginActivity() {
        startActivity(LoginActivity.getIntent(this))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }
}

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onClickAlreadyHasAnAccount: () -> Unit,
) {
    var showPassword by remember { mutableStateOf(false) }
    val passwordVisualizationMode = if (showPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    FormWithBeeHealthIdentity {
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = { Text(text = "Email") },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = {Text(text = "Senha") },
            visualTransformation = passwordVisualizationMode,
            trailingIcon = {
                PasswordTrailingIcon(showPassword = showPassword) { showPassword = !showPassword}
            },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.passwordConfirmation,
            onValueChange = { viewModel.passwordConfirmation = it },
            placeholder = { Text(text = "Confirmar senha") },
            visualTransformation = passwordVisualizationMode,
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.register()
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) { Text(text = "Entrar") }

        TextButton(onClick = { onClickAlreadyHasAnAccount() }) { Text(text = "Já tenho uma conta") }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen() {}
}