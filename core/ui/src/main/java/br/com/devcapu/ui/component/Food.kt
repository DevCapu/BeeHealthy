package br.com.devcapu.beehealthy.food.add.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.component.card.BeeCard
import br.com.devcapu.beehealthy.component.card.BeeCardHeader
import br.com.devcapu.ui.theme.BeeHealthyTheme
import br.com.devcapu.ui.theme.Carbohyd
import br.com.devcapu.ui.theme.Fats
import br.com.devcapu.ui.theme.Protein
import br.com.devcapu.beehealthy.uistate.FoodUiState
import kotlin.math.roundToInt

@Composable
fun Food(
    modifier: Modifier = Modifier,
    uiState: FoodUiState,
    onClick: (FoodUiState) -> Unit
) {
    BeeCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(uiState) },
        header = {
            BeeCardHeader(
                title = { Text(text = uiState.name) },
                subtitle = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            )
        },
        body = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    text = "${uiState.calories.toDouble().roundToInt()} kcal",
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )

                Chip(
                    text = "${uiState.carbohydrates.toDouble().roundToInt()}g",
                    backgroundColor = br.com.devcapu.ui.theme.Carbohyd,
                    contentColor = contentColorFor(backgroundColor = br.com.devcapu.ui.theme.Carbohyd)
                )

                Chip(
                    text = "${uiState.proteins.toDouble().roundToInt()}g",
                    backgroundColor = br.com.devcapu.ui.theme.Protein,
                    contentColor = contentColorFor(backgroundColor = br.com.devcapu.ui.theme.Protein)
                )

                Chip(
                    text = "${uiState.fats.toDouble().roundToInt()}g",
                    backgroundColor = br.com.devcapu.ui.theme.Fats,
                    contentColor = contentColorFor(backgroundColor = br.com.devcapu.ui.theme.Fats)
                )
            }
        }
    )
}

@Composable
private fun Chip(text: String, backgroundColor: Color, contentColor: Color) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun FoodPreview() {
    br.com.devcapu.ui.theme.BeeHealthyTheme {
        Food(
            uiState = FoodUiState(
                id = 0,
                name = "Avocado",
                measure = "g",
                calories = "136",
                carbohydrates = "78",
                proteins = "55",
                fats = "16"
            ),
            onClick = { }
        )
    }
}