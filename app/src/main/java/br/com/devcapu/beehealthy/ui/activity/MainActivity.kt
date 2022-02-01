package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.viewModel.HomeViewModel


class MainActivity : ComponentActivity() {

    private val factory by lazy {
        val dataSource = PatientDataSource(this)
        HomeViewModel.Factory(dataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme {
                HomeScreen()
            }
        }
    }

    @Composable
    fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = factory)) {
        Column {
            OutlinedTextField(value = viewModel.height,
                onValueChange = { viewModel.height = it },
                placeholder = {
                    Text(text = stringResource(R.string.height))
                })
            OutlinedTextField(value = viewModel.weight,
                onValueChange = { viewModel.weight = it },
                placeholder = {
                    Text(text = stringResource(R.string.weight))
                })

            Button(onClick = {
                viewModel.calculateBMI()
            }) {
                Text(text = stringResource(R.string.calculate_imc))
            }
        }
    }

    companion object{
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun HomeScreenPreview() {
        BeeHealthyTheme {
            HomeScreen()
        }
    }
}