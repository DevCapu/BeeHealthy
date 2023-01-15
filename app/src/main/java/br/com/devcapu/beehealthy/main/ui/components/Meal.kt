package br.com.devcapu.beehealthy.main.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.common.ui.component.CircularProgressIndicatorWithBackground
import br.com.devcapu.beehealthy.common.ui.theme.Carme
import br.com.devcapu.beehealthy.main.ui.state.MealUiState
import br.com.devcapu.beehealthy.main.ui.state.MealsUiState

@Composable
fun Meals(uiState: MealsUiState) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Meal(
            uiState.breakfastUiState,
            name = "Café da manhã",
            emoji = "\uD83C\uDF5E"
        )
        Meal(
            uiState.breakfastUiState,
            name = "Almoço",
            emoji = "\uD83C\uDF73"
        )
        Meal(
            uiState.breakfastUiState,
            name = "Café da tarde",
            emoji = "\uD83E\uDD6A"
        )
        Meal(
            uiState.breakfastUiState,
            name = "Janta",
            emoji = "\uD83E\uDED4"
        )
    }
}

@Composable
fun Meal(
    uiState: MealUiState,
    name: String,
    emoji: String,
) {
    val progress = if (uiState.ingested.toFloat() <= 0f || uiState.total.toFloat() <= 0f) {
        0f
    } else {
        uiState.ingested.toFloat().div(uiState.total.toFloat())
    }

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box {
                CircularProgressIndicatorWithBackground(
                    progress = progress,
                    color = MaterialTheme.colors.primary,
                    size = 40.dp,
                    strokeWidth = 4.dp
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = emoji,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Carme,
                    fontSize = 18.sp
                )
            }
            Column {
                Text(
                    text = name,
                    fontFamily = Carme,
                    fontSize = 16.sp
                )
                Text(
                    text = "${uiState.ingested} / ${uiState.total} cal",
                    fontFamily = Carme,
                    fontSize = 12.sp
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colors.primary
                        )
                        .padding(2.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }
    }
}