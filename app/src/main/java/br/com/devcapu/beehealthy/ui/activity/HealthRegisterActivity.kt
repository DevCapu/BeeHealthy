package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.ui.component.OutlineDropDownMenu
import br.com.devcapu.beehealthy.ui.viewModel.HealthRegisterViewModel

class HealthRegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HealthRegisterScreen() }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, HealthRegisterActivity::class.java)
    }
}

@Composable
fun HealthRegisterScreen(viewModel: HealthRegisterViewModel = viewModel()) {
    val genders = listOf("Masculino", "Feminino")
    val objectives = listOf("Perder", "Definir", "Manter")
    val activityLevel = listOf("Baixo", "Médio", "Alto")

    FormWithBeeHealthIdentity {
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            placeholder = { Text(text = "Name") },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.age,
            onValueChange = { viewModel.age = it },
            placeholder = { Text(text = "Age") },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.height,
            onValueChange = { viewModel.height = it },
            placeholder = { Text(text = "Altura") },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.weight,
            onValueChange = { viewModel.weight = it },
            placeholder = { Text(text = "peso") },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.height,
            onValueChange = { viewModel.height = it },
            placeholder = { Text(text = "Altura") },
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
        )

        OutlineDropDownMenu(
            values = genders,
            selectedIndex = 0,
            onChange = { viewModel.biologicGender = genders[it] },
            label = {
                Text(
                    text = "Sexo biológico",
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlineDropDownMenu(
            values = objectives,
            selectedIndex = 0,
            onChange = { viewModel.objective = objectives[it] },
            label = {
                Text(
                    text = "Objetivo",
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlineDropDownMenu(
            values = activityLevel,
            selectedIndex = 0,
            onChange = { viewModel.activityLevel = genders[it] },
            label = {
                Text(
                    text = "Nível de atividade",
                    color = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
        )

        Button(
            onClick = { /*TODO()*/ },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) { Text(text = "Pronto!") }
    }
}


@Preview
@Composable
fun HealthRegisterScreenPreview() {
    HealthRegisterScreen()
}