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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.data.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.OutlineDropDownMenu
import br.com.devcapu.beehealthy.ui.component.PasswordTrailingIcon
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel
import br.com.devcapu.domain.model.BiologicalGender

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
                    val genders = listOf("Masculino", "Feminino")
                    val objectives = listOf("Perder", "Definir", "Manter")
                    val activityLevel = listOf("Baixo", "Médio", "Alto")

                    HealthRegisterContent(
                        name = viewModel.name,
                        onNameChange = { viewModel.name = it },
                        age = viewModel.age,
                        onAgeChange = { viewModel.age = it },
                        height = viewModel.height,
                        onHeightChange = { viewModel.height = it },
                        weight = viewModel.weight,
                        onWeightChange = { viewModel.weight = it },
                        biologicalGenders = genders,
                        onBiologicalGenderChange = {
                            if (genders[it] == getString(R.string.male_option)) {
                                viewModel.biologicGender = BiologicalGender.MALE.toString()
                            } else {
                                viewModel.biologicGender = BiologicalGender.FEMALE.toString()
                            }
                        },
                        objectives = objectives,
                        onObjectiveChange = { viewModel.objective = objectives[it] },
                        activitiesLevel = activityLevel,
                        onActivityLevelChange = {
                            viewModel.activityLevel = activityLevel[it]
                        },
                        finishSignUp = { viewModel.finishSignUp() }
                    )
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
        ) { Text(text = "Entrar") }

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
    biologicalGenders: List<String>,
    onBiologicalGenderChange: (Int) -> Unit,
    objectives: List<String>,
    onObjectiveChange: (Int) -> Unit,
    activitiesLevel: List<String>,
    onActivityLevelChange: (Int) -> Unit,
    finishSignUp: () -> Unit,
) {
    FormWithBeeHealthIdentity(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text(text = "Name") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = onAgeChange,
            placeholder = { Text(text = "Age") },
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
            placeholder = { Text(text = "peso") },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        OutlineDropDownMenu(
            values = biologicalGenders,
            selectedIndex = 0,
            onChange = onBiologicalGenderChange,
            label = {
                Text(
                    text = "Sexo biológico",
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlineDropDownMenu(
            values = objectives,
            selectedIndex = 0,
            onChange = onObjectiveChange,
            label = {
                Text(
                    text = "Objetivo",
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlineDropDownMenu(
            values = activitiesLevel,
            selectedIndex = 0,
            onChange = onActivityLevelChange,
            label = {
                Text(
                    text = "Nível de atividade",
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Button(
            onClick = finishSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) { Text(text = "Pronto!") }
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