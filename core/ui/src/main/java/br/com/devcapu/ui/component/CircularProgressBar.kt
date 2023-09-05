package br.com.devcapu.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressIndicatorWithBackground(
    progress: Float,
    color: Color,
    size: Dp,
    strokeWidth: Dp = 8.dp,
) {
    Box {
        CircularProgressIndicator(
            progress = 1f,
            color = color.copy(alpha = 0.2f),
            strokeWidth = strokeWidth,
            modifier = Modifier.then(Modifier.size(size))
        )

        CircularProgressIndicator(
            progress = progress,
            color = color,
            strokeWidth = strokeWidth,
            modifier = Modifier.then(Modifier.size(size))
        )
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CircularProgressIndicatorWithBackground() {
    CircularProgressIndicatorWithBackground(
        progress = 0.5f,
        color = MaterialTheme.colors.primary,
        size = 48.dp
    )
}