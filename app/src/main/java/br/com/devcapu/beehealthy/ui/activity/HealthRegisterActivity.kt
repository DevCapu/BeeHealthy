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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.data.database.dataSource.HealthResultDataSource
import br.com.devcapu.beehealthy.data.database.dataSource.PatientDataSource
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.OutlineDropDownMenu
import br.com.devcapu.beehealthy.ui.viewModel.HealthRegisterViewModel
import br.com.devcapu.domain.model.BiologicalGender

class HealthRegisterActivity : ComponentActivity() {

    private val viewModel: HealthRegisterViewModel by viewModels {
        val patientDataSource = PatientDataSource(this)
        val healthResultDataSource = HealthResultDataSource(this)
        HealthRegisterViewModel.Factory(
            patientDataSource = patientDataSource,
            healthResultDataSource = healthResultDataSource
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email: String = intent.getStringExtra("PATIENT_EMAIL") as String
        viewModel.email = email

        val genders = listOf("Masculino", "Feminino")
        val objectives = listOf("Perder", "Definir", "Manter")
        val activityLevel = listOf("Baixo", "Médio", "Alto")

        setContent {
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
                onActivityLevelChange = { viewModel.activityLevel = activityLevel[it] },
                finishSignUp = { viewModel.finishSignUp() }
            )
        }

        viewModel.userCreated.observe(this) { wasCreated ->
            if (wasCreated) {
                startActivity(MainActivity.getIntent(this))
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, HealthRegisterActivity::class.java)
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


@Preview(showSystemUi = true)
@Composable
fun HealthRegisterScreenPreview() {
    HealthRegisterContent(
        name = "",
        onNameChange = { },
        age = "",
        onAgeChange = { },
        height = "",
        onHeightChange = { },
        weight = "",
        onWeightChange = { },
        biologicalGenders = listOf(""),
        onBiologicalGenderChange = { },
        objectives = listOf(""),
        onObjectiveChange = { },
        activitiesLevel = listOf(""),
        onActivityLevelChange = { },
        finishSignUp = { }
    )
}