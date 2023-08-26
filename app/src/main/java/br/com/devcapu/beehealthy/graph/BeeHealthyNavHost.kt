package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.screen.auth.login.navigateToLogin

@Composable
fun BeeHealthyNavHost() {
    val authNavController = rememberNavController()
    NavHost(
        navController = authNavController,
        startDestination = AUTH_GRAPH_ROUTE
    ) {
        authGraph(authNavController)
        mainNavGraph(
            onLogout = { authNavController.navigateToLogin()}
        )
    }
}