package br.com.devcapu.beehealthy.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.app.ui.theme.PrimaryFont
import br.com.devcapu.beehealthy.app.ui.uiState.HomeUIState

@Composable
fun HomeScreen(uiState: HomeUIState) = BeeHealthyTheme {
    Column(Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Greeting(name = uiState.name)

        Card(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Hoje",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "23 Junho",
                        style = MaterialTheme.typography.h3,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                MacroNutrientsProgressBar(caloriesToCommitObjective = uiState.caloriesToCommitObjective)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Carboidrato", modifier = Modifier.fillMaxWidth(0.3f))
                    Text(text = "100 g", modifier = Modifier.fillMaxWidth(0.3f))
                    Text(text = "32%", modifier = Modifier.fillMaxWidth(0.3f))
                }
                Divider(Modifier.padding(vertical = 2.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Proteína", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "90 g", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "40%", modifier = Modifier.fillMaxWidth(0.33f))
                }
                Divider(Modifier.padding(vertical = 2.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Gordura", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "100 g", modifier = Modifier.fillMaxWidth(0.33f))
                    Text(text = "32%", modifier = Modifier.fillMaxWidth(0.33f))
                }
            }
        }

        Card(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Refeições",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "2 de 5",
                        style = MaterialTheme.typography.h3,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                LinearProgressIndicator(
                    progress = 0.20f,
                    color = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun MacroNutrientsProgressBar(caloriesToCommitObjective: Double) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = caloriesToCommitObjective.toInt().toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                fontFamily = PrimaryFont
            )
            Text(
                text = "calorias",
                fontSize = 12.sp
            )
        }
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = 0.45f,
                color = Red,
                modifier = Modifier
                    .then(Modifier.size(96.dp))
                    .clip(CircleShape)
            )

            CircularProgressIndicator(
                progress = 0.55f,
                color = Green,
                modifier = Modifier.then(Modifier.size(112.dp))
            )

            CircularProgressIndicator(
                progress = 0.75f,
                color = Blue,
                modifier = Modifier.then(Modifier.size(128.dp))
            )
        }
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
        carbs = 34,
        protein = 21,
        fat = 32
    )
)