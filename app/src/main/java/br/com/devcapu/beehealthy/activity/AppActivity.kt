package br.com.devcapu.beehealthy.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.devcapu.beehealthy.graph.BeeHealthyNavHost
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme {
               BeeHealthyNavHost()
            }
        }
    }
}