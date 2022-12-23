package br.com.devcapu.beehealthy.main.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.common.ui.component.CompoundCircularProgressBar
import br.com.devcapu.beehealthy.common.ui.component.RowCell
import br.com.devcapu.beehealthy.common.ui.component.card.CardHeader
import br.com.devcapu.beehealthy.common.ui.component.card.PrimaryCard
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.ui.theme.PrimaryFont
import br.com.devcapu.beehealthy.main.ui.HomeUIState
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(uiState: HomeUIState) = BeeHealthyTheme {
    val formatter = SimpleDateFormat("dd/mm/YYYY")
    val current = formatter.format(Calendar.getInstance().time)
    Column(Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        PrimaryCard(
            header = { CardHeader(title = "Hoje", subtitle = current) },
            body = {
                CompoundCircularProgressBar(
                    text = {
                        Text(
                            text = uiState.caloriesToCommitObjective.toInt().toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp,
                            fontFamily = PrimaryFont
                        )
                        Text(
                            text = "calorias",
                            fontSize = 12.sp
                        )
                    },
                    progressBarList = uiState.progressBar,
                    size = 96.dp
                )
                uiState.macros.forEachIndexed { index, macro ->
                    RowCell(macro = macro)
                    if (index != uiState.macros.lastIndex) {
                        Divider(Modifier.padding(vertical = 6.dp))
                    }
                }
            }
        )

        PrimaryCard(
            header = { CardHeader(title = "Refeições", subtitle = "2 de 5") },
            body = {
                LinearProgressIndicator(
                    progress = 0.20f,
                    color = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        )
    }
}

@Preview(showSystemUi = true)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun HomePreview() {
    HomeScreen(HomeUIState(
        name = "Felipe",
        caloriesToCommitObjective = 2030.00
    ))
}