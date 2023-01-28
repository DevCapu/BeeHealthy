package br.com.devcapu.beehealthy.component.card

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
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
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme

@Composable
fun CardHeader(title: String, subtitle: String = "") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.h3,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview(name = "With title and subtitle", showBackground = true)
@Preview(
    name = "With title and subtitle",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun CardHeaderWithTitleAndSubtilePreview() {
    BeeHealthyTheme {
        CardHeader(title = "Refeições", subtitle = "3 de 5")
    }
}

@Preview(name = "With title", showBackground = true)
@Preview(
    name = "With title",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun CardHeaderWithTitlePreview() {
    BeeHealthyTheme {
        CardHeader(title = "Refeições")
    }
}