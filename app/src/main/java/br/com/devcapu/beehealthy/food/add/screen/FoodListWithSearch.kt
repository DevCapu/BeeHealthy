package br.com.devcapu.beehealthy.food.add.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.common.ui.component.OutlineInput
import br.com.devcapu.beehealthy.food.add.components.Food

@Composable
fun FoodListWithSearch(
    onClickFood: () -> Unit,
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            OutlineInput(
                modifier = Modifier.background(MaterialTheme.colors.background.copy(alpha = 0.4f)),
                value = "",
                onValueChange = {},
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                placeholder = {
                    Text("Procure por um alimento")
                },
            )
        }

        repeat(12) {
            item {
                Food(onClick = onClickFood)
            }
        }
    }
}