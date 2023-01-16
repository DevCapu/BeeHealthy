package br.com.devcapu.beehealthy.food.add.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.common.ui.theme.Carme

@Composable
fun AppBar(
    onClickGoBack: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onClickGoBack
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
            }
            Column(Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Café da manhã",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = Carme
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sexta feira 13",
                    textAlign = TextAlign.Center,
                    fontFamily = Carme,
                    color = contentColorFor(
                        backgroundColor = MaterialTheme.colors.background
                    ).copy(alpha = 0.4f),
                    fontSize = 12.sp,
                )
            }
        }
    }
}