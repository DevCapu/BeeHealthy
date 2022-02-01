package br.com.devcapu.beehealthy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.viewModel.LoginViewModel

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isSignedIn()) {
            startActivity(MainActivity.getIntent(this))
        }
    }

    @Composable
    fun LoginScreen() {
        BeeHealthyTheme {
            Column {

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Bee with a plus sign icon"
                )

                Text(text = "Don't worry")
                Text(text = "Bee Healthy")

                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    placeholder = {
                        Text(text = "Email")
                    }
                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = {
                        Text(text = "Senha")
                    }
                )

                Button(onClick = { viewModel.signIn() }) { Text(text = "Entrar") }
                TextButton(onClick = { /*TODO*/ }) { Text(text = "Não tem conta ainda? Criar!") }
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun LoginScreenPreview() {
        LoginScreen()
    }
}