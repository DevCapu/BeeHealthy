package br.com.devcapu.beehealthy.screen.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.component.Meals
import br.com.devcapu.beehealthy.component.card.BeeCard
import br.com.devcapu.beehealthy.component.card.BeeCardHeader
import br.com.devcapu.beehealthy.food.nutrition.components.NutritionStats
import br.com.devcapu.ui.theme.BeeHealthyTheme
import br.com.devcapu.ui.theme.Carme
import br.com.devcapu.beehealthy.usecase.DiaryUiState
import br.com.devcapu.beehealthy.viewmodel.DiaryViewModel
import br.com.devcapu.beehealthy.viewmodel.DiaryViewModel.Companion.Factory

const val DIARY_SCREEN_ROUTE = "DIARY_SCREEN_ROUTE"

fun NavGraphBuilder.diaryScreen() {
    composable(route = DIARY_SCREEN_ROUTE) {
        val viewModel: DiaryViewModel = viewModel(
            viewModelStoreOwner = LocalContext.current as ComponentActivity,
            factory = Factory
        )
        val uiState by viewModel.state.collectAsState()

        DiaryScreen(uiState = uiState)
    }
}

@Composable
fun DiaryScreen(uiState: DiaryUiState) = br.com.devcapu.ui.theme.BeeHealthyTheme {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { NutritionCard(uiState) }
        item {
            MealCard(uiState = uiState)
        }
    }
}

@Composable
private fun NutritionCard(uiState: DiaryUiState) {
    BeeCard(
        header = {
            BeeCardHeader(
                title = {
                    Text(
                        text = stringResource(R.string.nutrition_label),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = br.com.devcapu.ui.theme.Carme,
                        fontSize = 22.sp
                    )
                },
                subtitle = {
                    Text(
                        modifier = Modifier.clickable { },
                        text = stringResource(R.string.see_details_label),
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold,
                        fontFamily = br.com.devcapu.ui.theme.Carme,
                        fontSize = 14.sp
                    )
                }
            )
        },
        body = { NutritionStats(uiState = uiState) }
    )
}

@Composable
private fun MealCard(uiState: DiaryUiState) {
    BeeCard(
        header = {
            BeeCardHeader(
                title = {
                    Text(
                        text = stringResource(R.string.meals_label),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = br.com.devcapu.ui.theme.Carme,
                        fontSize = 22.sp
                    )
                },
            )
        },
        body = {
            Meals(uiState = uiState.mealsUiState)
        }
    )
}

@Preview(showSystemUi = true)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun DiaryScreePreview() {
    DiaryScreen(uiState = DiaryUiState())
}