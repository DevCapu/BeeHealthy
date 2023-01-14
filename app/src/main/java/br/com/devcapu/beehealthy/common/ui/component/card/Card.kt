package br.com.devcapu.beehealthy.common.ui.component.card

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.common.ui.component.CircularProgressIndicatorWithBackground
import br.com.devcapu.beehealthy.common.ui.theme.*
import androidx.compose.ui.text.style.TextAlign.Companion.Center as TextCenter

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
    subtitle: @Composable () -> Unit,
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

@Composable
fun NutritionStats() {
    Column(Modifier.fillMaxWidth()) {
        CaloriesConsumption()
        MacroRow()
    }
}

@Composable
private fun CaloriesConsumption() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Column(
            verticalArrangement = SpaceBetween,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "546",
                fontWeight = Bold,
                fontFamily = Carme,
                fontSize = 18.sp
            )
            Text(
                text = "Consumidas",
                fontWeight = SemiBold,
                fontFamily = Carme,
                fontSize = 16.sp
            )
        }
        Box(contentAlignment = Center) {
            Column(
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "1243",
                    fontWeight = SemiBold,
                    fontFamily = Carme,
                    fontSize = 20.sp
                )
                Text(
                    text = "cal",
                    fontWeight = SemiBold,
                    fontFamily = Carme,
                    fontSize = 16.sp
                )
            }
            CircularProgressIndicatorWithBackground(
                progress = 0.56f,
                color = MaterialTheme.colors.primary,
                size = 90.dp
            )
        }
        Column(
            verticalArrangement = SpaceBetween,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "1000",
                fontWeight = Bold,
                fontFamily = Carme,
                fontSize = 18.sp
            )
            Text(
                text = "Gastas",
                fontWeight = SemiBold,
                fontFamily = Carme,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun MacroRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        MacroIngestionToday(
            label = stringResource(id = R.string.carbohyd_label),
            progress = .56f,
            color = Carbohyd,
            total = 234f
        )
        MacroIngestionToday(
            label = stringResource(id = R.string.protein_label),
            progress = .2f,
            color = Protein,
            total = 234f
        )
        MacroIngestionToday(
            label = stringResource(id = R.string.fat_label),
            progress = .8f,
            color = Fats,
            total = 234f
        )
    }
}

@Composable
private fun MacroIngestionToday(
    label: String,
    progress: Float,
    color: Color,
    total: Float,
) {
    Column(
        modifier = Modifier.width(96.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = label,
            fontSize = 16.sp,
            fontWeight = SemiBold,
            fontFamily = Carme,
            textAlign = TextCenter
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            progress = progress,
            color = color,
            backgroundColor = color.copy(alpha = 0.4f),
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$total g",
            fontSize = 14.sp,
            fontWeight = Bold,
            fontFamily = Carme,
            textAlign = TextCenter
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CardPreview() {
    BeeHealthyTheme {
        val onClick: () -> Unit = { }
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
                            modifier = Modifier.clickable { onClick() },
                            text = "Ver detalhes",
                            color = Color(0xFF2196F3),
                            fontWeight = Bold,
                            fontFamily = Carme,
                            fontSize = 14.sp
                        )
                    }
                )
            },
            body = { NutritionStats() }
        )
    }
}