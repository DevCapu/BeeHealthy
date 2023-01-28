package br.com.devcapu.beehealthy.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.theme.Carbohyd
import br.com.devcapu.beehealthy.diary.ui.state.UIMacro

@Composable
fun RowCell(macro: UIMacro) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        TextWithColorSample(
            text = stringResource(id = macro.name),
            color = macro.color
        )
        Text(
            text = "${macro.weight} g",
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth(0.33f))
        Text(
            text = "${macro.percentage.times(100)}%",
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RowCellPreview() {
    RowCell(
        macro = UIMacro(
            color = Carbohyd,
            name = R.string.carbohyd_label,
            weight = 234,
            percentage = 0.5f
        )
    )
}