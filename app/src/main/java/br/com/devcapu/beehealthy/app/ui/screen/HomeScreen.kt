package br.com.devcapu.beehealthy.register.ui.screen

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
import br.com.devcapu.beehealthy.app.ui.component.CompoundCircularProgressBar
import br.com.devcapu.beehealthy.app.ui.component.RowCell
import br.com.devcapu.beehealthy.app.ui.component.card.CardHeader
import br.com.devcapu.beehealthy.app.ui.component.card.PrimaryCard
import br.com.devcapu.beehealthy.app.ui.theme.*
import br.com.devcapu.beehealthy.app.ui.uiState.HomeUIState
import br.com.devcapu.beehealthy.app.ui.uiState.UIMacro
import br.com.devcapu.beehealthy.app.ui.uiState.ProgressBar

@Composable
fun HomeScreen(uiState: HomeUIState) = BeeHealthyTheme {
    Column(Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Greeting(name = uiState.name)

        PrimaryCard(
            header = { CardHeader(title = "Hoje", subtitle = "23 de Junho") },
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
                    progressBarList = uiState.progressBar
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
            body = {  LinearProgressIndicator(
                progress = 0.20f,
                color = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )}
        )
    }
}

@Composable
private fun Greeting(name: String) {
    Text(
        text = "Bem vindo!",
        fontSize = 18.sp,
        style = MaterialTheme.typography.h3
    )
    Text(
        text = name,
        fontSize = 32.sp,
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() = HomeScreen(
    HomeUIState(
        name = "João",
        caloriesToCommitObjective = 2342.24,
        progressBar = listOf(
            ProgressBar(progress = 0.45f, color = Carbohyd, size = 96.dp),
            ProgressBar(progress = 0.65f, color = Protein, size = 120.dp),
            ProgressBar(progress = 0.79f, color = Fats, size = 144.dp)
        ),
        macros = listOf(
            UIMacro(
                color = Carbohyd,
                name = "Carboidrato",
                weight = 32,
                percentage = 0.43f
            ),
            UIMacro(
                color = Protein,
                name = "Proteína",
                weight = 21,
                percentage = 0.23f
            ),
            UIMacro(
                color = Fats,
                name = "Gordura",
                weight = 56,
                percentage = 0.31f
            ),
        )
    )
)