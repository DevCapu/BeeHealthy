package br.com.devcapu.beehealthy.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import br.com.devcapu.beehealthy.main.ui.UIMacro

@Composable
fun RowCell(macro: UIMacro) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextWithColorSample(
            text = macro.name,
            color = macro.color
        )
        Text(
            text = "${macro.weight} g",
            modifier = Modifier.fillMaxWidth(0.33f))
        Text(
            text = "${macro.percentage.times(100)}%",
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
    }
}