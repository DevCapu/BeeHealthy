package br.com.devcapu.beehealthy.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.main.ui.ProgressBar

@Composable
fun CompoundCircularProgressBar(
    text: @Composable () -> Unit,
    progressBarList: List<ProgressBar>,
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
            progressBarList.forEach {
                CircularBar(
                    progress = it.progress,
                    color = it.color,
                    size = it.size
                )
            }
        }
    }
}