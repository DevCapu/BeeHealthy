package br.com.devcapu.beehealthy.food.nutrition.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.component.CircularProgressIndicatorWithBackground
import br.com.devcapu.ui.theme.Carbohyd
import br.com.devcapu.ui.theme.Carme
import br.com.devcapu.ui.theme.Fats
import br.com.devcapu.ui.theme.Protein
import br.com.devcapu.beehealthy.uistate.CaloriesUiState
import br.com.devcapu.beehealthy.uistate.MacroUiState
import br.com.devcapu.beehealthy.uistate.MacrosUiState
import br.com.devcapu.beehealthy.usecase.DiaryUiState


@Composable
fun NutritionStats(uiState: DiaryUiState) {
    Column(Modifier.fillMaxWidth()) {
        CaloriesConsumption(uiState = uiState.caloriesUiState)
        MacroRow(uiState.macrosUiState)
    }
}

@Composable
fun CaloriesConsumption(uiState: CaloriesUiState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LabelWithValue(
            label = stringResource(id = R.string.consumption_label),
            value = uiState.consumedCalories
        )
        Box(contentAlignment = Alignment.Center) {
            val progress =
                if (uiState.remainingCalories.toFloat() <= 0f || uiState.caloriesToCommitObjective.toFloat() <= 0f) {
                    0f
                } else {
                    uiState.remainingCalories.toFloat()
                        .div(uiState.caloriesToCommitObjective.toFloat())
                }
            CircularProgressIndicatorWithBackground(
                progress = progress,
                color = MaterialTheme.colors.primary,
                size = 90.dp
            )
            LabelWithValue(
                label = stringResource(id = R.string.remaining_label),
                value = uiState.remainingCalories
            )
        }
        LabelWithValue(
            label = stringResource(id = R.string.expended_label),
            value = uiState.expendedCalories
        )
    }
}

@Composable
fun MacroRow(macros: MacrosUiState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MacroIngestionToday(
            label = stringResource(id = R.string.carbohyd_label),
            color = br.com.devcapu.ui.theme.Carbohyd,
            uiState = macros.carbohydrateUiState,
        )

        MacroIngestionToday(
            label = stringResource(id = R.string.protein_label),
            color = br.com.devcapu.ui.theme.Protein,
            uiState = macros.proteinUiState,
        )

        MacroIngestionToday(
            label = stringResource(id = R.string.fat_label),
            color = br.com.devcapu.ui.theme.Fats,
            uiState = macros.fatUiState,
        )
    }
}

@Composable
private fun MacroIngestionToday(
    label: String,
    color: Color,
    uiState: MacroUiState,
) {
    Column(
        modifier = Modifier.width(96.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = br.com.devcapu.ui.theme.Carme,
            textAlign = TextAlign.Center
        )

        val progress = if (uiState.ingested.toFloat() <= 0f || uiState.total.toFloat() <= 0f) {
            0f
        } else {
            uiState.ingested.toFloat().div(uiState.total.toFloat())
        }

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(8.dp)),
            progress = progress,
            color = color,
            backgroundColor = color.copy(alpha = 0.4f),
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${uiState.ingested} / ${uiState.total} g",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = br.com.devcapu.ui.theme.Carme,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LabelWithValue(label: String, value: String) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontFamily = br.com.devcapu.ui.theme.Carme,
            fontSize = 18.sp
        )
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontFamily = br.com.devcapu.ui.theme.Carme,
            fontSize = 12.sp
        )
    }
}