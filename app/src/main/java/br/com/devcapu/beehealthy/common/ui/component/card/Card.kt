package br.com.devcapu.beehealthy.common.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryCard(
    header: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
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
            header()
            body()
        }
    }

}