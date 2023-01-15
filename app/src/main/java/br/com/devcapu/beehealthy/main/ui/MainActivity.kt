package br.com.devcapu.beehealthy.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.devcapu.beehealthy.common.JsonUtil
import br.com.devcapu.beehealthy.common.data.repository.PatientRepository
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.config.BeeHealthyDatabase
import br.com.devcapu.beehealthy.main.ui.screen.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels {
        val patientRepository = PatientRepository(BeeHealthyDatabase.getInstance(this).patientDao())
        HomeViewModel.Factory(patientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.findAllCategories(JsonUtil.getJsonFromAssets(this@MainActivity, "categoryList.json")!!)
        }
        setContent {
            BeeHealthyTheme { MainScreen(viewModel) }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}