import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun MealCard() {
    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(0.dp).fillMaxWidth()) {
            CarHeader()
            repeat(3) {
                Column(Modifier.padding(10.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Caldo de mocotó")
                        Text(text = "100 Kcal")
                    }
                    Row {
                        Text(
                            text = "56g",
                            fontSize = 14.sp,
//                            color = Color(0xFF4444444)
                        )
                    }
                    Divider(Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp))
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
            CardFooter()
        }

    }
}

@Composable
private fun CardFooter() {
    Row {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Adicionar alimento")
        }
    }
}

@Composable
private fun CarHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary).padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(text = "Café da manhã")
            Text(text = "245.45 Kcal")
        }
        IconToggleButton(
            checked = true,
            onCheckedChange = { /*TODO*/ }) {
            Image(
                imageVector = Icons.Default.ArrowDownward,
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            )
        }
    }
}
