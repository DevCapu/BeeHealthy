package br.com.devcapu.beehealthy.register.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.config.BeeHealthyDatabase
import br.com.devcapu.beehealthy.main.ui.MainActivity
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.data.repository.HealthRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.login.ui.LoginActivity
import br.com.devcapu.beehealthy.register.ui.screen.*
import br.com.devcapu.beehealthy.register.ui.screen.OnboardSteps.*
import kotlinx.coroutines.launch

class RegisterActivity : ComponentActivity() {
    private val viewModel: RegisterViewModel by viewModels {
        val database = BeeHealthyDatabase.getInstance(this)
        RegisterViewModel.Factory(
            patientRepository = PatientRepository(database.patientDao()),
            healthRepository = HealthRepository(database.healthResultDao())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            LaunchedEffect(key1 = Unit) {
                launch {
                    viewModel.uiState.collect {
                        if (it.finished) {
                            goToHomeActivity()
                        }
                    }
                }

                launch {
                    viewModel.stepState.collect {
                        navController.navigate(it.step.name)
                    }
                }
            }
            BeeHealthyTheme {
                NavHost(
                    navController = navController,
                    startDestination = AUTHENTICATION_REGISTER.name
                ) {
                    composable(AUTHENTICATION_REGISTER.name) {
                        AuthRegisterScreen(viewModel)
                    }
                    composable(USER_REGISTER_FORM.name) {
                        UserRegisterScreen(viewModel)
                    }
                    composable(BIOLOGICAL_GENDER_SELECTION.name) {
                        GenderSelectionScreen(viewModel)
                    }
                    composable(OBJECTIVE_SELECTION.name) {
                        ObjectiveSelectionScreen(viewModel)
                    }
                    composable(ACTIVITY_LEVEL_SELECTION.name) {
                        ActivityLevelSelectionScreen(viewModel)
                    }
                }
            }
        }
    }

    private fun goToHomeActivity() {
        startActivity(MainActivity.getIntent(this))
    }

    private fun goToLoginActivity() {
        startActivity(LoginActivity.getIntent(this))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }
}
