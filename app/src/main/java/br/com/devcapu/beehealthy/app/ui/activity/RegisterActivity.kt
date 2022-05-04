package br.com.devcapu.beehealthy.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.app.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.app.ui.screen.onboarding.*
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.app.ui.viewModel.RegisterViewModel
import br.com.devcapu.beehealthy.domain.repository.HealthRepository
import br.com.devcapu.beehealthy.domain.repository.PatientRepository

class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels {
        val database = BeeHealthyDatabase.getInstance(this)
        val patientRepository = PatientRepository(database.patientDao())
        val healthRepository = HealthRepository(database.healthResultDao())
        RegisterViewModel.Factory(patientRepository = patientRepository,
            healthRepository = healthRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            addObservers(navController)
            BeeHealthyTheme {
                NavHost(
                    navController = navController,
                    startDestination = OnboardSteps.AUTHENTICATION_REGISTER.name
                ) {
                    composable(OnboardSteps.AUTHENTICATION_REGISTER.name) {
                        AuthenticationLoginScreen(viewModel)
                    }
                    composable(OnboardSteps.USER_REGISTER_FORM.name) {
                        UserRegisterFormScreen(viewModel)
                    }
                    composable(OnboardSteps.BIOLOGICAL_GENDER_SELECTION.name) {
                        GenderSelectionScreen(viewModel)
                    }
                    composable(OnboardSteps.OBJECTIVE_SELECTION.name) {
                        ObjectiveSelectionScreen(viewModel)
                    }
                    composable(OnboardSteps.ACTIVITY_LEVEL_SELECTION.name) {
                        ActivityLevelScreenSelection(viewModel)
                    }
                }
            }
        }
    }

    private fun addObservers(navController: NavHostController) {
        viewModel.step.observe(this) {
            navController.navigate(it)
        }

        viewModel.finished.observe(this) { finished ->
            if (finished) {
                startActivity(MainActivity.getIntent(this))
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