package br.com.devcapu.beehealthy.screen.auth.register

import androidx.activity.ComponentActivity
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
import androidx.compose.ui.platform.LocalContext
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
import br.com.devcapu.beehealthy.model.patient.health.ActivityLevel.ACTIVE
import br.com.devcapu.beehealthy.model.patient.health.ActivityLevel.LOW
import br.com.devcapu.beehealthy.model.patient.health.ActivityLevel.MODERATE
import br.com.devcapu.beehealthy.model.patient.health.ActivityLevel.SEDENTARY
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.uistate.RegisterUIState
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel

private const val ACTIVITY_LEVEL_SCREEN_SELECTION_ROUTE = "ACTIVITY_LEVEL_SELECTION_SCREEN_ROUTE"

fun NavController.navigateToActivityLevelSelection() =
    navigate(ACTIVITY_LEVEL_SCREEN_SELECTION_ROUTE)

fun NavGraphBuilder.activityLevelSelectionScreen(
    onClickNextStep: () -> Unit
) {
    composable(route = ACTIVITY_LEVEL_SCREEN_SELECTION_ROUTE) {
        val viewModel: RegisterViewModel = viewModel(
            viewModelStoreOwner = LocalContext.current as ComponentActivity,
            factory = RegisterViewModel.Factory
        )
        val uiState by viewModel.uiState.collectAsState()

        ActivityLevelSelectionScreen(
            state = uiState,
            onClickNextStep = {
                viewModel.savePatient()
                onClickNextStep()
            }
        )
    }
}

@Composable
fun ActivityLevelSelectionScreen(
    state: RegisterUIState,
    onClickNextStep: () -> Unit
) {
    Column(
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
            onClick = onClickNextStep,
            shape = RoundedCornerShape(4.dp),
        ) { Text(text = stringResource(id = R.string.next_step)) }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ActivityLevelScreenPreview() {
    BeeHealthyTheme {
        ActivityLevelSelectionScreen(RegisterUIState()) { }
    }
}