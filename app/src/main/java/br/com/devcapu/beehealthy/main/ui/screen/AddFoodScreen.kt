package br.com.devcapu.beehealthy.main.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.common.ui.component.OutlineInput
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme

@Composable
fun AddFoodScreen() {
    var searchedValue by remember { mutableStateOf("") }
    var foods by remember { mutableStateOf(emptyList<Food>()) }
    foods = listOf(
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
        Food("Banana", "1 Unidade", 123.00),
    )

    BeeHealthyTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlineInput(
                value = searchedValue,
                placeholder = {
                    Text(text = "Search...")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search icon",
                        modifier = Modifier.clickable {
                            //Search Food
                        }
                    )
                },
                onValueChange = { searchedValue = it },
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(Modifier.fillMaxWidth()) {
                items(foods) { food: Food ->
                    Row(
                        horizontalArrangement = SpaceBetween,
                        verticalAlignment = CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = food.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = food.measure,
                                fontWeight = FontWeight.ExtraLight,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            text = food.calories.toString(),
                            fontWeight = FontWeight.Light,
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                //Add food
                            }
                        )
                    }
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AddFoodScreenPreview() {
    AddFoodScreen()
}

data class Food(
    val name: String,
    val measure: String,
    val calories: Double,
)