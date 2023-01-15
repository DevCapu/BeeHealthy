package br.com.devcapu.beehealthy.food.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import br.com.devcapu.beehealthy.common.ui.component.card.BeeCard
import br.com.devcapu.beehealthy.common.ui.component.card.BeeCardHeader
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.ui.theme.Carbohyd
import br.com.devcapu.beehealthy.common.ui.theme.Fats
import br.com.devcapu.beehealthy.common.ui.theme.Protein

@Composable
fun Food(
    modifier: Modifier = Modifier,
) {
    BeeCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        header = {
            BeeCardHeader(
                title = { Text(text = "Alimento") },
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
                    text = "120 kcal",
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )

                Chip(
                    text = "0.5g",
                    backgroundColor = Carbohyd,
                    contentColor = contentColorFor(backgroundColor = Carbohyd)
                )

                Chip(
                    text = "0.2g",
                    backgroundColor = Protein,
                    contentColor = contentColorFor(backgroundColor = Protein)
                )

                Chip(
                    text = "0.1g",
                    backgroundColor = Fats,
                    contentColor = contentColorFor(backgroundColor = Fats)
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
    BeeHealthyTheme {
        Food()
    }
}