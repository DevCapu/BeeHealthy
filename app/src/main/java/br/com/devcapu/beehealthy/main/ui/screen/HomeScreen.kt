package br.com.devcapu.beehealthy.main.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.common.ui.component.card.BeeCard
import br.com.devcapu.beehealthy.common.ui.component.card.BeeCardHeader
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.ui.theme.Carme
import br.com.devcapu.beehealthy.main.ui.components.Meals
import br.com.devcapu.beehealthy.main.ui.components.NutritionStats
import br.com.devcapu.beehealthy.main.ui.state.HomeUIState

@Composable
fun HomeScreen(uiState: HomeUIState) = BeeHealthyTheme {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { NutritionCard(uiState) }
        item { MealCard(uiState) }
    }
}

@Composable
private fun NutritionCard(uiState: HomeUIState) {
    BeeCard(
        header = {
            BeeCardHeader(
                title = {
                    Text(
                        text = stringResource(R.string.nutrition_label),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Carme,
                        fontSize = 22.sp
                    )
                },
                subtitle = {
                    Text(
                        modifier = Modifier.clickable { },
                        text = stringResource(R.string.see_details_label),
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Carme,
                        fontSize = 14.sp
                    )
                }
            )
        },
        body = { NutritionStats(uiState = uiState) }
    )
}

@Composable
private fun MealCard(uiState: HomeUIState) {
    BeeCard(
        header = {
            BeeCardHeader(
                title = {
                    Text(
                        text = stringResource(R.string.meals_label),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Carme,
                        fontSize = 22.sp
                    )
                },
            )
        },
        body = { Meals(uiState.mealsUiState) }
    )
}

@Preview(showSystemUi = true)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun HomePreview() {
    HomeScreen(HomeUIState())
}