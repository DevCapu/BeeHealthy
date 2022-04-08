package br.com.devcapu.beehealthy.ui.screen.onboarding

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
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel

@Composable
fun ActivityLevelScreenSelection(viewModel: RegisterViewModel) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
) {
    ActivityLevelContent {
        viewModel.activityLevel = it
        viewModel.signUp()
    }
}

@Composable
fun ActivityLevelContent(
    onClickToGoToNextStep: (String) -> Unit,
) = Column(
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
    }

    SelectionCard(text = "Leve", selected = lightLevelSelected) {
        sedentaryLevelSelected = false
        lightLevelSelected = true
        moderateLevelSelected = false
        activeLevelSelected = false
    }

    SelectionCard(text = "Moderado", selected = moderateLevelSelected) {
        sedentaryLevelSelected = false
        lightLevelSelected = false
        moderateLevelSelected = true
        activeLevelSelected = false
    }

    SelectionCard(text = "Ativo", selected = activeLevelSelected) {
        sedentaryLevelSelected = false
        lightLevelSelected = false
        moderateLevelSelected = false
        activeLevelSelected = true
    }

    Button(
        onClick = {
            val activityLevelSelected = ""

            onClickToGoToNextStep(activityLevelSelected)
        },
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) { Text(text = stringResource(id = R.string.next_step)) }
}

@Preview(showSystemUi = true)
@Composable
fun ActivityLevelScreenPreview() {
    ActivityLevelContent {}
}