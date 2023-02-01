package br.com.devcapu.beehealthy.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.graph.BeeHealthyNavHost
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme {
               BeeHealthyNavHost(navHostController = rememberNavController())
            }
        }
    }
}