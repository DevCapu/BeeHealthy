package br.com.devcapu.beehealthy.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.app.ui.component.CompoundCircularProgressBar
import br.com.devcapu.beehealthy.app.ui.component.TextWithColorSample
import br.com.devcapu.beehealthy.app.ui.component.card.CardHeader
import br.com.devcapu.beehealthy.app.ui.component.card.PrimaryCard
import br.com.devcapu.beehealthy.app.ui.theme.*
import br.com.devcapu.beehealthy.app.ui.uiState.HomeUIState
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
                CompoundCircularProgressBar(text = {
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithColorSample(text = "Carboidratos", color = Carbohyd)
                    Text(text = "100 g", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "32%", modifier = Modifier.fillMaxWidth(0.33f))
                }
                Divider(Modifier.padding(vertical = 2.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithColorSample(text = "Proteína", color = Protein)
                    Text(text = "90 g", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "40%", modifier = Modifier.fillMaxWidth(0.33f))
                }
                Divider(Modifier.padding(vertical = 2.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithColorSample(text = "Gordura", color = Fats)
                    Text(text = "100 g", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "32%", modifier = Modifier.fillMaxWidth(0.33f))
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
        )
    )
)