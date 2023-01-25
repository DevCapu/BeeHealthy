package br.com.devcapu.beehealthy.authentication.register.ui.screen

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
import br.com.devcapu.beehealthy.authentication.register.ui.RegisterViewModel
import br.com.devcapu.beehealthy.authentication.register.ui.state.RegisterUIState
import br.com.devcapu.beehealthy.common.domain.model.patient.health.Objective.*
import br.com.devcapu.beehealthy.common.ui.component.SelectionCard
import br.com.devcapu.beehealthy.common.ui.component.SelectionTitle
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme

@Composable
fun ObjectiveSelectionScreen(
    viewModel: RegisterViewModel = viewModel(),
    onClickNextStep: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    ObjectiveSelectionContent(
        state = state,
        onClickNextStep = onClickNextStep
    )
}

@Composable
fun ObjectiveSelectionContent(
    state: RegisterUIState,
    onClickNextStep: () -> Unit
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
) {

    SelectionTitle("Qual o seu", "objetivo?")

    var loseWeightSelected by remember { mutableStateOf(false) }
    var defineBodySelected by remember { mutableStateOf(false) }
    var gainMassSelected by remember { mutableStateOf(false) }

    val loseWeightLabel = stringResource(R.string.lose_weight_label)
    val defineBodyLabel = stringResource(R.string.define_body_label)
    val gainMassLabel = stringResource(R.string.gain_mass_label)

    SelectionCard(text = loseWeightLabel, selected = loseWeightSelected) {
        loseWeightSelected = true
        defineBodySelected = false
        gainMassSelected = false

        state.onObjectiveChanged(LOSE)
    }

    SelectionCard(text = defineBodyLabel, selected = defineBodySelected) {
        loseWeightSelected = false
        defineBodySelected = true
        gainMassSelected = false

        state.onObjectiveChanged(MAINTAIN)
    }

    SelectionCard(text = gainMassLabel, selected = gainMassSelected) {
        loseWeightSelected = false
        defineBodySelected = false
        gainMassSelected = true

        state.onObjectiveChanged(GAIN)
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = onClickNextStep,
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(text = stringResource(id = R.string.next_step))
    }
}

@Preview(showSystemUi = true)
@Composable
fun ObjectiveSelectionScreenPreview() {
    BeeHealthyTheme {
        ObjectiveSelectionContent(RegisterUIState()) { }
    }
}