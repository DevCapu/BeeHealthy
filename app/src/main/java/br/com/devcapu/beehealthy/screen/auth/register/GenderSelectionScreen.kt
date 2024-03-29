package br.com.devcapu.beehealthy.screen.auth.register

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.model.patient.health.BiologicalGender.FEMALE
import br.com.devcapu.beehealthy.model.patient.health.BiologicalGender.MALE
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.theme.PrimaryFont
import br.com.devcapu.beehealthy.uistate.RegisterUIState
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel.Companion.Factory

const val GENDER_SELECTION_SCREEN_ROUTE = "GENDER_SELECTION_SCREEN_ROUTE"

fun NavController.navigateToGenderSelection() = navigate(GENDER_SELECTION_SCREEN_ROUTE)

fun NavGraphBuilder.genderSelectionScreen(onClickNextStep: () -> Unit) {
    composable(route = GENDER_SELECTION_SCREEN_ROUTE) {
        val viewModel: RegisterViewModel = viewModel(
            viewModelStoreOwner = LocalContext.current as ComponentActivity,
            factory = Factory
        )
        val uiState by viewModel.uiState.collectAsState()

        GenderSelectionScreen(
            state = uiState,
            onClickNextStep = onClickNextStep
        )
    }
}

@Composable
fun GenderSelectionScreen(
    state: RegisterUIState,
    onClickNextStep: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var maleCardIsSelected by remember { mutableStateOf(false) }
        var femaleCardIsSelected by remember { mutableStateOf(false) }
        var showNoGenderSelectedError by remember { mutableStateOf(false) }

        Text(
            text = stringResource(R.string.gender_selection_title),
            fontSize = 36.sp,
            fontFamily = PrimaryFont,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
        ) {
            GenderCard(
                icon = Icons.Filled.Male,
                title = stringResource(R.string.male_label),
                isSelected = maleCardIsSelected,
                onClick = {
                    maleCardIsSelected = true
                    femaleCardIsSelected = false
                    showNoGenderSelectedError = false
                    state.onBiologicalGenderChanged(MALE)
                }
            )

            GenderCard(
                icon = Icons.Filled.Female,
                title = stringResource(R.string.female_label),
                isSelected = femaleCardIsSelected,
                onClick = {
                    maleCardIsSelected = false
                    femaleCardIsSelected = true
                    showNoGenderSelectedError = false
                    state.onBiologicalGenderChanged(FEMALE)
                }
            )
        }

        Button(onClick = onClickNextStep) {
            Text(stringResource(id = R.string.next_step))
        }

        if (showNoGenderSelectedError) {
            Text(
                text = stringResource(R.string.did_not_choose_an_option),
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Composable
fun GenderCard(icon: ImageVector, title: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        border = if (isSelected) BorderStroke(1.dp, MaterialTheme.colors.secondary) else null,
        modifier = Modifier.clickable { onClick() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                imageVector = icon,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "$title icon",
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PrimaryFont,
                color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelectGenderPreview() {
    BeeHealthyTheme {
        GenderSelectionScreen(RegisterUIState()) { }
    }
}