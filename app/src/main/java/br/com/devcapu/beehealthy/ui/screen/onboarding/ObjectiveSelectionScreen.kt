package br.com.devcapu.beehealthy.ui.screen.onboarding

import android.util.Log
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
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.ui.component.SelectionCard
import br.com.devcapu.beehealthy.ui.component.SelectionTitle
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel

@Composable
fun ObjectiveSelectionScreen(viewModel: RegisterViewModel) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
) {
    BeeHealthyTheme {
        ObjectiveSelectionContent {
            viewModel.objective = it
            viewModel.goTo(OnboardSteps.ACTIVITY_LEVEL_SELECTION.name)
        }
    }
}

@Composable
fun ObjectiveSelectionContent(
    onClickToGoToNextStep: (String) -> Unit,
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
    }

    SelectionCard(text = defineBodyLabel, selected = defineBodySelected) {
        loseWeightSelected = false
        defineBodySelected = true
        gainMassSelected = false
    }

    SelectionCard(text = gainMassLabel, selected = gainMassSelected) {
        loseWeightSelected = false
        defineBodySelected = false
        gainMassSelected = true
    }

    Button(
        onClick = {
            val objectiveSelected = when {
                loseWeightSelected -> loseWeightLabel
                defineBodySelected -> defineBodyLabel
                else -> gainMassLabel
            }

            onClickToGoToNextStep(objectiveSelected)
        },
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) { Text(text = stringResource(id = R.string.next_step)) }
}

@Preview(showSystemUi = true)
@Composable
fun ObjectiveSelectionScreenPreview() {
    BeeHealthyTheme {
        ObjectiveSelectionContent { }
    }
}