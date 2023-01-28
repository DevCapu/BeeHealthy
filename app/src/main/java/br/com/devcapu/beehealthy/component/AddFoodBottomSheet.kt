package br.com.devcapu.beehealthy.food.add.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.food.add.state.FoodUiState
import br.com.devcapu.beehealthy.theme.*
import kotlin.math.roundToInt

@Composable
fun AddFoodBottomSheet(
    uiState: FoodUiState,
    onClickAdd: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FoodNameWithStar(name = uiState.name)
        QuantityIngested()
        if (uiState.calories.isEmpty()) {
            Log.e("EMPTY_CALORIE", uiState.name)
            println(uiState)
            println()
            println()
            println()
        }
        FoodPropertiesGrid(
            calories = uiState.calories,
            carbohydrate = uiState.carbohydrates,
            protein = uiState.proteins,
            fats = uiState.fats
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(8.dp),
            onClick = onClickAdd
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(R.string.add_label),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Carme,
                color = contentColorFor(backgroundColor = MaterialTheme.colors.surface)
            )
        }
    }
}

@Composable
private fun FoodNameWithStar(name: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Carme,
            color = contentColorFor(backgroundColor = MaterialTheme.colors.surface)
        )
        Icon(
            modifier = Modifier.align(Alignment.CenterEnd),
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun QuantityIngested() {
    Row(Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = { }
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun FoodPropertiesGrid(
    calories: String,
    carbohydrate: String,
    protein: String,
    fats: String
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            FoodProperty(
                icon = painterResource(id = R.drawable.calories),
                label = stringResource(id = R.string.calories_label),
                value = "${calories.toDouble().roundToInt()} kcal",
                tintColor = MaterialTheme.colors.primary
            )
        }
        item {
            FoodProperty(
                icon = painterResource(id = R.drawable.carbs),
                label = stringResource(id = R.string.carbohyd_label),
                value = "${carbohydrate.toDouble().roundToInt()}g",
                tintColor = Carbohyd
            )
        }

        item {
            FoodProperty(
                icon = painterResource(id = R.drawable.fat),
                label = stringResource(id = R.string.fat_label),
                value = "${fats.toDouble().roundToInt()}g",
                tintColor = Fats
            )
        }

        item {
            FoodProperty(
                icon = painterResource(id = R.drawable.protein),
                label = stringResource(id = R.string.protein_label),
                value = "${protein.toDouble().roundToInt()}g",
                tintColor = Protein
            )
        }
    }
}

@Composable
private fun FoodProperty(
    icon: Painter,
    label: String,
    value: String,
    tintColor: Color,
) {
    Surface(
        color = Color.Transparent,
        contentColor = contentColorFor(MaterialTheme.colors.onSurface),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = icon,
                contentDescription = null,
                tint = tintColor
            )
            Column {
                Text(
                    text = label,
                    fontSize = 12.sp,
                    fontFamily = Carme,
                    color = contentColorFor(backgroundColor = MaterialTheme.colors.surface)
                )
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Carme,
                    color = contentColorFor(backgroundColor = MaterialTheme.colors.surface)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AddFoodBottomSheetPreview() {
    BeeHealthyTheme {
        AddFoodBottomSheet(uiState = FoodUiState()) { }
    }
}