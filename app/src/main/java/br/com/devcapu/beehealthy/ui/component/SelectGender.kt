package br.com.devcapu.beehealthy.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.theme.PrimaryFont

@Composable
fun SelectGender(
    onClick: (String) -> Unit,
    onClickGoToNextStep: () -> Unit,
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()
) {
    var maleCardIsSelected by remember { mutableStateOf(false) }
    var femaleCardIsSelected by remember { mutableStateOf(false) }
    var showNoGenderSelectedError by remember { mutableStateOf(false) }

    Text(
        text = "Selecione seu gênero biológico",
        fontSize = 36.sp,
        fontFamily = PrimaryFont,
        color = MaterialTheme.colors.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    ) {
        GenderCard(
            icon = Icons.Filled.Male,
            title = "Masculino",
            isSelected = maleCardIsSelected,
            onClick = {
                maleCardIsSelected = true
                femaleCardIsSelected = false
                showNoGenderSelectedError = false
                onClick("Masculino")
            }
        )

        GenderCard(
            icon = Icons.Filled.Female,
            title = "Feminino",
            isSelected = femaleCardIsSelected,
            onClick = {
                maleCardIsSelected = false
                femaleCardIsSelected = true
                showNoGenderSelectedError = false
                onClick("Feminino")
            }
        )
    }

    Button(
        onClick = {
            if (maleCardIsSelected or femaleCardIsSelected) {
                onClickGoToNextStep()
            } else {
                showNoGenderSelectedError = true
            }
        }
    ) { Text("Próximo passo") }

    if (showNoGenderSelectedError){
        Text(
            text = "Nenhuma opção selecionada",
            color = MaterialTheme.colors.error,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
fun GenderCard(icon: ImageVector, title: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        border = if (isSelected) BorderStroke(1.dp, MaterialTheme.colors.secondary) else null,
        modifier = Modifier.clickable { onClick() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                imageVector = icon,
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "$title icon",
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = PrimaryFont
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelectGenderPreview() {
    BeeHealthyTheme {
        SelectGender({}, {})
    }
}