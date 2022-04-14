package br.com.devcapu.beehealthy.app.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.app.ui.theme.PrimaryFont

@Composable
fun SelectionTitle(
    questionInit: String,
    questionEnd: String,
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Text(
        text = questionInit,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = PrimaryFont
    )
    Text(
        text = questionEnd,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        color = MaterialTheme.colors.primary,
        fontFamily = PrimaryFont,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}


@Preview
@Composable
fun SelectionTitlePreview() {
    SelectionTitle(questionInit = "Qual o seu", questionEnd = "nome?")
}