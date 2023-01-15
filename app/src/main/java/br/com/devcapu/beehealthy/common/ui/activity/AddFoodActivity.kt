package br.com.devcapu.beehealthy.common.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.devcapu.beehealthy.common.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.main.ui.screen.AddFoodScreen

class AddFoodActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme {
                AddFoodScreen()
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, AddFoodActivity::class.java)
    }
}