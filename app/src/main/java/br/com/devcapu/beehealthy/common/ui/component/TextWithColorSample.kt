package br.com.devcapu.beehealthy.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.common.ui.theme.Protein

@Composable
fun TextWithColorSample(text: String, color: Color) {
    Row(modifier = Modifier.fillMaxWidth(.5f)) {
        Spacer(modifier = Modifier
            .size(20.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(4.dp)
            ))
        Text(
            text,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() = TextWithColorSample(text = "Proteína", color = Protein)
