package br.com.devcapu.beehealthy.app.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.app.ui.component.MealCard
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.domain.model.Meal

@Composable
fun HomeScreen() {
    BeeHealthyTheme {
        LazyColumn(Modifier
            .padding(8.dp)
            .fillMaxWidth()) {
            items(listOf(
                Meal(name = "Café da manhã", 456),
                Meal(name = "Almoço", 456),
                Meal(name = "Café da tarde", 456),
                Meal(name = "Jantar", 456)
            )) {
                MealCard(name = it.name, calories = it.calories)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() = HomeScreen()