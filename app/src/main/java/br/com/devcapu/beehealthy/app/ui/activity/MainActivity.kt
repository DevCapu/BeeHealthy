package br.com.devcapu.beehealthy.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.app.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.app.ui.component.MealCard
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.app.ui.viewModel.HomeViewModel
import br.com.devcapu.beehealthy.domain.model.Meal
import br.com.devcapu.beehealthy.domain.repository.PatientRepository


class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels {
        val repository = PatientRepository(BeeHealthyDatabase.getInstance(this).patientDao())
        HomeViewModel.Factory(repository)
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
        Scaffold(
            content = {
                LazyColumn(Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                ) {
                   items(listOf(
                       Meal(name = "Café da manhã", 456),
                       Meal(name = "Almoço", 456),
                       Meal(name = "Café da tarde", 456),
                       Meal(name = "Jantar", 456)
                   )) {
                       MealCard(name = it.name, calories = it.calories)
                   }
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScreen()
}