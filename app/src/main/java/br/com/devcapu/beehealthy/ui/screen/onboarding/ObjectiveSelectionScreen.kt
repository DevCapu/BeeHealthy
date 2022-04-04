package br.com.devcapu.beehealthy.ui.screen.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.theme.PrimaryFont
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
        ObjectiveSelectionContent(
        ) {
            viewModel.objective = it
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

    SelectionTitle()

    var loseWeightSelected by remember { mutableStateOf(false) }
    var defineBodySelected by remember { mutableStateOf(false) }
    var gainMassSelected by remember { mutableStateOf(false) }

    val loseWeightLabel = stringResource(R.string.lose_weight_label)
    val defineBodyLabel = stringResource(R.string.define_body_label)
    val gainMassLabel = stringResource(R.string.gain_mass_label)

    SelectionCard(objective = loseWeightLabel, selected = loseWeightSelected) {
        loseWeightSelected = true
        defineBodySelected = false
        gainMassSelected = false
    }

    SelectionCard(objective = defineBodyLabel, selected = defineBodySelected) {
        loseWeightSelected = false
        defineBodySelected = true
        gainMassSelected = false
    }

    SelectionCard(objective = gainMassLabel, selected = gainMassSelected) {
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
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
    ) { Text(text = stringResource(id = R.string.next_step)) }
}

@Composable
private fun SelectionCard(
    objective: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(4.dp),
        border = if (selected) {
            BorderStroke(2.dp, color = Color.Green)
        } else {
            null
        },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = objective,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun SelectionTitle() {
    Text(
        text = "Qual o seu",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = PrimaryFont
    )
    Text(
        text = "Objetivo?",
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = PrimaryFont,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun ObjectiveSelectionScreenPreview() {
    BeeHealthyTheme {
        ObjectiveSelectionContent() { }
    }
}