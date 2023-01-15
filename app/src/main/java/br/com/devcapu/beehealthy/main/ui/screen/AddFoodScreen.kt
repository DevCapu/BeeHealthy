package br.com.devcapu.beehealthy.main.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphNavigator
import br.com.devcapu.beehealthy.common.ui.component.OutlineInput
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.common.ui.theme.Carme

@Composable
fun AddFoodScreen(
    navigator: NavGraphNavigator
) {
    AddFoodScreen(
        onClickGoBack = { navigator.popBackStack() }
    )
}

@Composable
fun AddFoodScreen(
    onClickGoBack: () -> Unit
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        topBar = { AddFoodAppBar(onClickGoBack) }
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
        }
    }
}

@Composable
private fun AddFoodAppBar(
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

@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AddFoodScreenPreview() {
    BeeHealthyTheme {
        AddFoodScreen { }
    }
}

data class Food(
    val name: String,
    val measure: String,
    val calories: Double,
)