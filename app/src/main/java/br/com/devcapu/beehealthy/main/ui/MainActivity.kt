package br.com.devcapu.beehealthy.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import br.com.devcapu.beehealthy.config.BeeHealthyDatabase
import br.com.devcapu.beehealthy.main.ui.screen.MainScreen
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.login.ui.LoginActivity

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels {
        val patientRepository = PatientRepository(BeeHealthyDatabase.getInstance(this).patientDao())
        HomeViewModel.Factory(patientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme { MainScreen(viewModel) { logout() } }
        }
    }

    private fun logout() {
        viewModel.logout()
        val intent = LoginActivity.getIntent(this)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}