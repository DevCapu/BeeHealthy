package br.com.devcapu.beehealthy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.ui.theme.BeeHealthyTheme

@Composable
fun FormWithBeeHealthIdentity(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    BeeHealthyTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.beehealthylogo),
                contentDescription = "Bee with a plus sign icon",
                modifier = Modifier
                    .size(128.dp)
                    .padding(bottom = 16.dp)
            )

            Text(text = "Don't worry", style = MaterialTheme.typography.h2)
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            content()
        }
    }
}