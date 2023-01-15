package br.com.devcapu.beehealthy.common.ui.component.card

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.ui.theme.Carme
import br.com.devcapu.beehealthy.main.ui.components.NutritionStats
import br.com.devcapu.beehealthy.main.ui.state.CaloriesUiState
import br.com.devcapu.beehealthy.main.ui.state.HomeUIState

@Composable
fun BeeCard(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    body: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = SpaceEvenly
        ) {
            header()
            body()
        }
    }
}

@Composable
fun BeeCardHeader(
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit = { },
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        title()
        subtitle()
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CardPreview() {
    BeeHealthyTheme {
        BeeCard(
            header = {
                BeeCardHeader(
                    title = {
                        Text(
                            text = "Nutrição",
                            fontWeight = SemiBold,
                            fontFamily = Carme,
                            fontSize = 22.sp
                        )
                    },
                    subtitle = {
                        Text(
                            modifier = Modifier.clickable { },
                            text = "Ver detalhes",
                            color = Color(0xFF2196F3),
                            fontWeight = Bold,
                            fontFamily = Carme,
                            fontSize = 14.sp
                        )
                    }
                )
            },
            body = {
                NutritionStats(
                    uiState = HomeUIState(
                        caloriesUiState = CaloriesUiState(
                            consumedCalories = "659",
                            remainingCalories = "1245",
                            expendedCalories = "300"
                        )
                    )
                )
            }
        )
    }
}