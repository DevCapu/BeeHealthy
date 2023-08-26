package br.com.devcapu.beehealthy.screen.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.component.SelectionCard
import br.com.devcapu.beehealthy.component.SelectionTitle
import br.com.devcapu.beehealthy.model.patient.health.Objective.GAIN
import br.com.devcapu.beehealthy.model.patient.health.Objective.LOSE
import br.com.devcapu.beehealthy.model.patient.health.Objective.MAINTAIN
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.uistate.RegisterUIState
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel.Companion.Factory

private const val OBJECTIVE_SELECTION_SCREEN_ROUTE = "OBJECTIVE_SELECTION_SCREEN_ROUTE"

fun NavController.navigateToObjectiveSelection() = navigate(OBJECTIVE_SELECTION_SCREEN_ROUTE)

fun NavGraphBuilder.objectiveSelectionScreen(onClickNextStep: () -> Unit) {
    composable(route = OBJECTIVE_SELECTION_SCREEN_ROUTE) {
        val viewModel: RegisterViewModel = viewModel(factory = Factory)
        val uiState by viewModel.uiState.collectAsState()

        ObjectiveSelectionScreen(
            state = uiState,
            onClickNextStep = onClickNextStep
        )
    }
}

@Composable
fun ObjectiveSelectionScreen(
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
        ObjectiveSelectionScreen(RegisterUIState()) { }
    }
}