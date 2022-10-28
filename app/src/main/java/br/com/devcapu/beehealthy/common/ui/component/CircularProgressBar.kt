package br.com.devcapu.beehealthy.common.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularBar(
    progress: Float,
    color: Color,
    size: Dp,
) {
    CircularProgressIndicator(
        progress = 1f,
        color = color.copy(alpha = 0.2f),
        strokeWidth = 8.dp,
        modifier = Modifier.then(Modifier.size(size))
    )

    CircularProgressIndicator(
        progress = progress,
        color = color,
        strokeWidth = 8.dp,
        modifier = Modifier.then(Modifier.size(size))
    )
}