package br.com.devcapu.beehealthy.food.meal.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.component.CircularProgressIndicatorWithBackground
import br.com.devcapu.beehealthy.food.meal.state.MealUiState
import br.com.devcapu.beehealthy.food.meal.state.MealsUiState
import br.com.devcapu.beehealthy.graph.MainScreens
import br.com.devcapu.beehealthy.theme.Carme

@Composable
fun Meals(
    uiState: MealsUiState,
    mainNavController: NavController
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Meal(
            uiState.breakfastUiState,
            name = stringResource(R.string.breakfast_label),
            emoji = "\uD83C\uDF5E"
        ) { mainNavController.navigate(MainScreens.AddFoodScreen.screen_route) }
        Meal(
            uiState.lunchUiState,
            name = stringResource(R.string.lunch_label),
            emoji = "\uD83C\uDF73"
        ) { mainNavController.navigate(MainScreens.AddFoodScreen.screen_route) }
        Meal(
            uiState.snackUiState,
            name = stringResource(R.string.snack_label),
            emoji = "\uD83E\uDD6A"
        ) { mainNavController.navigate(MainScreens.AddFoodScreen.screen_route) }
        Meal(
            uiState.dinnerUiState,
            name = stringResource(R.string.dinner_label),
            emoji = "\uD83E\uDED4"
        ) { mainNavController.navigate(MainScreens.AddFoodScreen.screen_route + "/1") }
    }
}

@Composable
fun Meal(
    uiState: MealUiState,
    name: String,
    emoji: String,
    onClick: () -> Unit,
) {
    val progress = if (uiState.ingested.toFloat() <= 0f || uiState.total.toFloat() <= 0f) {
        0f
    } else {
        uiState.ingested.toFloat().div(uiState.total.toFloat())
    }

    Surface(modifier = Modifier.fillMaxWidth()) {
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
                    text = uiState.ingested + " / " + uiState.total + " cal",
                    fontFamily = Carme,
                    fontSize = 12.sp
                )

            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onClick) {
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