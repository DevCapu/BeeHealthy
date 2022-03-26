package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.data.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.ui.component.SelectGender
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel

class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels {
        val patientDataSource = PatientDataSource(this)
        val healthResultDataSource = HealthResultDataSource(this)
        RegisterViewModel.Factory(
            patientDataSource = patientDataSource,
            healthResultDataSource = healthResultDataSource
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            BeeHealthyTheme {
                NavHost(navController = navController, startDestination = "emailAndPassword") {
                    composable("emailAndPassword") {

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
                            onClickRegisterButton = {
                                viewModel.initSignUp()
                                    .observe(this@RegisterActivity) { userHasBeenCreated ->
                                        if (userHasBeenCreated) {
                                            navController.navigate("initialInfoForm")
                                        }
                                    }
                            },
                            onClickAlreadyHasAnAccount = { goToLoginActivity() }
                        )
                    }
                    composable("initialInfoForm") {
                        HealthRegisterContent(
                            name = viewModel.name,
                            onNameChange = { viewModel.name = it },
                            age = viewModel.age,
                            onAgeChange = { viewModel.age = it },
                            height = viewModel.height,
                            onHeightChange = { viewModel.height = it },
                            weight = viewModel.weight,
                            onWeightChange = { viewModel.weight = it },
                            finishSignUp = {
                                navController.navigate("genderSelection")
                            }
                        )
                    }
                    composable("genderSelection") {
                        SelectGender(onClick = {
                            viewModel.biologicGender = it
                        }) {
                            navController.navigate("objectiveSelection")
                        }
                    }
                    composable("objectiveSelection") {
                        Text("Activity level")
                    }
                    composable("activityLevel") {
                        Text("Activity level")
                    }
                }
            }
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
            onValueChange = onPasswordChange,
            placeholder = { Text(text = "Senha") },
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
            placeholder = { Text(text = "Confirmar senha") },
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
        ) { Text(text = "Próximo passo") }

        TextButton(onClick = onClickAlreadyHasAnAccount) { Text(text = "Já tenho uma conta") }
    }
}

@Composable
fun HealthRegisterContent(
    name: String,
    onNameChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    height: String,
    onHeightChange: (String) -> Unit,
    weight: String,
    onWeightChange: (String) -> Unit,
    finishSignUp: () -> Unit,
) {
    FormWithBeeHealthIdentity(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text(text = "Nome") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = onAgeChange,
            placeholder = { Text(text = "Idade") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = height,
            onValueChange = onHeightChange,
            placeholder = { Text(text = "Altura") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = weight,
            onValueChange = onWeightChange,
            placeholder = { Text(text = "Peso") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = finishSignUp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) { Text(text = "Próximo passo") }
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HealthRegisterScreenPreview() {
    HealthRegisterContent(
        name = "",
        onNameChange = {},
        weight = "",
        onWeightChange = {},
        height = "",
        onHeightChange = {},
        age = "",
        onAgeChange = {},
        finishSignUp = {}
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SelectGenderScreenPreview() {
    BeeHealthyTheme {
        SelectGender({}) {}
    }
}