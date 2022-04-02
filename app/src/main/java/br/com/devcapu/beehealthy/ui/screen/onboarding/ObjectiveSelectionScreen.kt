package br.com.devcapu.beehealthy.ui.screen.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.ui.theme.PrimaryFont
import br.com.devcapu.beehealthy.ui.viewModel.RegisterViewModel

@Composable
fun ObjectiveSelectionScreen(viewModel: RegisterViewModel) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
) {
    val objectives = arrayListOf<String>()
    objectives.add("Perder")
    objectives.add("Definir")
    objectives.add("Ganhar")

    BeeHealthyTheme {
        ObjectiveSelectionContent(objectives) {
            viewModel.objective = it
        }
    }
}

@Composable
fun ObjectiveSelectionContent(objectives: List<String>, onClick: (String) -> Unit) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
) {
    SelectionTitle()

    ListOfCards(titles = objectives) { clickedPosition ->
        onClick(objectives[clickedPosition])
    }

    Button(
        onClick = {},
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) { Text(text = stringResource(id = R.string.next_step)) }
}

@Composable
private fun SelectionCard(title: String, onClick: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ListOfCards(titles: List<String>, onClick: (Int) -> Unit) {
    LazyColumn {
        itemsIndexed(titles) { index, title ->
            SelectionCard(title) {
                onClick(index)
            }
        }
    }
}

@Composable
private fun SelectionTitle() {
    Text(
        text = "Qual o seu",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = PrimaryFont
    )
    Text(
        text = "Objetivo?",
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = PrimaryFont,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun ObjectiveSelectionScreenPreview() {
    val objectives = arrayListOf<String>()
    objectives.add("Perder")
    objectives.add("Definir")
    objectives.add("Ganhar")
    BeeHealthyTheme {
        ObjectiveSelectionContent(objectives) { }
    }
}