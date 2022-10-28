package br.com.devcapu.beehealthy.common.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CardHeader(title: String, subtitle: String = "") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.h3,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(name = "With title and subtitle", showBackground = true)
@Composable
fun CardHeaderWithTitleAndSubtilePreview() {
    CardHeader(title = "Refeições", subtitle = "3 de 5")
}

@Preview(name = "With title", showBackground = true)
@Composable
fun CardHeaderWithTitlePreview() {
    CardHeader(title = "Refeições")
}