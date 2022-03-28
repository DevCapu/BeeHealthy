package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.data.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.ui.screen.onboarding.AuthenticationLoginScreen
import br.com.devcapu.beehealthy.ui.screen.onboarding.GenderSelectionScreen
import br.com.devcapu.beehealthy.ui.screen.onboarding.OnboardSteps
import br.com.devcapu.beehealthy.ui.screen.onboarding.UserRegisterFormScreen
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
            viewModel.step.observe(this) {
                navController.navigate(it)
            }
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
                        Text("Objective level")
                    }
                    composable(OnboardSteps.ACTIVITY_LEVEL_SELECTION.name) {
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