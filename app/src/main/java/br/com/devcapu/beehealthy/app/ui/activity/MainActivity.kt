package br.com.devcapu.beehealthy.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.app.database.BeeHealthyDatabase
import br.com.devcapu.beehealthy.app.ui.component.BottomNavItem
import br.com.devcapu.beehealthy.app.ui.screen.BodyScreen
import br.com.devcapu.beehealthy.app.ui.screen.HomeScreen
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
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Bee Healthy") },
                            actions = {
                                IconButton(onClick = { viewModel.logout() }) {
                                    Icon(
                                        imageVector = Icons.Default.Logout,
                                        contentDescription = "Logout"
                                    )
                                }
                            }

                        )
                    },
                    bottomBar = { BottomNavigationBar(navController = navController) }
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

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) { HomeScreen() }
        composable(BottomNavItem.Body.screen_route) { BodyScreen() }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Body,
    )

    BottomNavigation(contentColor = Color.Black) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title, fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = { changeBottomTab(navController, item) }
            )
        }
    }
}

private fun changeBottomTab(navController: NavController, item: BottomNavItem) {
    navController.navigate(item.screen_route) {

        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}