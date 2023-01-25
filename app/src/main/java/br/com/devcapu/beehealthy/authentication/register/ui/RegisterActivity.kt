package br.com.devcapu.beehealthy.authentication.register.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import br.com.devcapu.beehealthy.authentication.login.ui.LoginActivity
import br.com.devcapu.beehealthy.common.data.repository.HealthRepository
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.config.BeeHealthyDatabase
import br.com.devcapu.beehealthy.main.MainActivity
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
            BeeHealthyTheme {
                LaunchedEffect(key1 = Unit) {
                    launch {
                        viewModel.uiState.collect {
                            if (it.finished) {
                                goToHomeActivity()
                            }
                        }
                    }
                }
                RegistrationNavGraph(viewModel)
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
