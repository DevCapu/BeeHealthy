package br.com.devcapu.beehealthy.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.viewModel.LoginViewModel

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.signedIn.observe(this) {
            if (it) {
                goToHomeActivity()
            }
        }

        setContent {
            LoginScreen()
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

    @Composable
    fun LoginScreen() {
        var showPassword by remember { mutableStateOf(false) }

        BeeHealthyTheme {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.beehealthylogo),
                    contentDescription = "Bee with a plus sign icon",
                    modifier = Modifier
                        .size(128.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "Don't worry",
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = "Bee Healthy",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    placeholder = {
                        Text(text = "Email")
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = {
                        Text(text = "Senha")
                    },
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            if (showPassword) {
                                Icon(imageVector = Icons.Outlined.Visibility,
                                    contentDescription = "Eye open")
                            } else {
                                Icon(imageVector = Icons.Outlined.VisibilityOff,
                                    contentDescription = "Eye open")
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.signIn()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(text = "Entrar")
                }

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