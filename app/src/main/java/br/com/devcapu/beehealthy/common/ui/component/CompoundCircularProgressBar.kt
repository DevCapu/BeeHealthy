package br.com.devcapu.beehealthy.common.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.common.ui.theme.Carbohyd
import br.com.devcapu.beehealthy.common.ui.theme.Fats
import br.com.devcapu.beehealthy.common.ui.theme.Protein
import br.com.devcapu.beehealthy.main.ui.ProgressBar

@Composable
fun CompoundCircularProgressBar(
    text: @Composable () -> Unit,
    progressBarList: List<ProgressBar>,
    size: Dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { text() }
        Box(contentAlignment = Alignment.Center) {
            progressBarList.forEachIndexed { index, item ->
                CircularBar(
                    progress = item.progress,
                    color = item.color,
                    size = size.plus(Dp(index * 24.toFloat()))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun CompoundCircularProgressBarPreview() {
    CompoundCircularProgressBar(
        text = { Text(text = "Macros") },
        progressBarList = listOf(
            ProgressBar(progress = 0.5f, color = Carbohyd),
            ProgressBar(progress = 0.5f, color = Protein),
            ProgressBar(progress = 0.5f, color = Fats)
        ),
        size = 96.dp
    )
}