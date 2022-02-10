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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.ui.viewModel.LoginViewModel

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.signedIn.observe(this) { isSignedIn ->
            if (isSignedIn) {
                goToHomeActivity()
            }
        }

        setContent {
            LoginScreen { goToRegisterActivity() }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.isSignedIn()) return
        goToHomeActivity()
    }

    private fun goToHomeActivity() {
        startActivity(MainActivity.getIntent(this))
    }

    private fun goToRegisterActivity() {
        startActivity(RegisterActivity.getIntent(this))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), onClickOnRegisterButton: () -> Unit) {
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
            placeholder = { Text(text = "Senha") },
            visualTransformation = passwordVisualizationMode,
            trailingIcon = { PasswordTrailingIcon(showPassword) { showPassword = !showPassword} },
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
        )

        Button(
            onClick = { viewModel.signIn() },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) { Text(text = "Entrar") }

        TextButton(
            onClick = { onClickOnRegisterButton() }
        ) { Text(text = "Não tem conta ainda? Criar!") }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen() {}
}