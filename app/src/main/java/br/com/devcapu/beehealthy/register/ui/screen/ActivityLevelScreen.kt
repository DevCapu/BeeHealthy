package br.com.devcapu.beehealthy.register.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.app.ui.component.SelectionCard
import br.com.devcapu.beehealthy.app.ui.component.SelectionTitle
import br.com.devcapu.beehealthy.register.ui.state.RegisterUIState
import br.com.devcapu.beehealthy.register.ui.RegisterViewModel
import br.com.devcapu.beehealthy.domain.model.patient.health.ActivityLevel.*

@Composable
fun ActivityLevelSelectionScreen(viewModel: RegisterViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    ActivityLevelSelectionScreen(state = state)
}

@Composable
fun ActivityLevelSelectionScreen(state: RegisterUIState) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
) {
    var sedentaryLevelSelected by remember { mutableStateOf(false) }
    var lightLevelSelected by remember { mutableStateOf(false) }
    var moderateLevelSelected by remember { mutableStateOf(false) }
    var activeLevelSelected by remember { mutableStateOf(false) }


    SelectionTitle("Qual o seu", "nível de atividade?")

    SelectionCard(text = "Sedentário", selected = sedentaryLevelSelected) {
        sedentaryLevelSelected = true
        lightLevelSelected = false
        moderateLevelSelected = false
        activeLevelSelected = false

        state.onActivityLevelChanged(SEDENTARY)
    }

    SelectionCard(text = "Leve", selected = lightLevelSelected) {
        sedentaryLevelSelected = false
        lightLevelSelected = true
        moderateLevelSelected = false
        activeLevelSelected = false

        state.onActivityLevelChanged(LOW)
    }

    SelectionCard(text = "Moderado", selected = moderateLevelSelected) {
        sedentaryLevelSelected = false
        lightLevelSelected = false
        moderateLevelSelected = true
        activeLevelSelected = false

        state.onActivityLevelChanged(MODERATE)
    }

    SelectionCard(text = "Ativo", selected = activeLevelSelected) {
        sedentaryLevelSelected = false
        lightLevelSelected = false
        moderateLevelSelected = false
        activeLevelSelected = true

        state.onActivityLevelChanged(ACTIVE)
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = state.onGoToNextStep,
        shape = RoundedCornerShape(4.dp),
    ) { Text(text = stringResource(id = R.string.next_step)) }
}

@Preview(showSystemUi = true)
@Composable
fun ActivityLevelScreenPreview() {
    ActivityLevelSelectionScreen(RegisterUIState())
}