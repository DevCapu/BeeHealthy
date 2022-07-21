package br.com.devcapu.beehealthy.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.app.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.app.ui.component.BottomBar
import br.com.devcapu.beehealthy.app.ui.component.TopBar
import br.com.devcapu.beehealthy.app.ui.navigation.NavigationGraph
import br.com.devcapu.beehealthy.app.ui.theme.BeeHealthyTheme
import br.com.devcapu.beehealthy.app.ui.viewModel.HomeViewModel
import br.com.devcapu.beehealthy.domain.repository.PatientRepository

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels {
        val repository = PatientRepository(BeeHealthyDatabase.getInstance(this).patientDao())
        HomeViewModel.Factory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeeHealthyTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { TopBar {
                        viewModel.logout()
                        val intent = LoginActivity.getIntent(this)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    } },
                    floatingActionButton = {
                        FloatingActionButton(
                            backgroundColor = Color(0xFF8463E5),
                            contentColor = Color.White,
                            onClick = { /*TODO*/ }
                        ) {
                            Image(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true,
                    bottomBar = { BottomBar(navController = navController) }
                ) {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}