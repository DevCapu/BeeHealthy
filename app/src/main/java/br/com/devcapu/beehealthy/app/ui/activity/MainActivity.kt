package br.com.devcapu.beehealthy.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.devcapu.beehealthy.app.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.app.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.app.ui.viewModel.HomeViewModel


class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels {
        val patientDataSource = PatientDataSource(this)
        val healthResultDataSource = HealthResultDataSource(this)
        HomeViewModel.Factory(patientDataSource, healthResultDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme {
                HomeScreen()
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}

@Composable
fun HomeScreen() {
    BeeHealthyTheme {
        Scaffold {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    BeeHealthyTheme {
        HomeScreen()
    }
}