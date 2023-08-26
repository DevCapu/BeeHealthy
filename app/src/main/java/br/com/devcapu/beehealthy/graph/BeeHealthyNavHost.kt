package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun BeeHealthyNavHost() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = AUTH_GRAPH_ROUTE
    ) {
        authGraph(navHostController)
    }
}