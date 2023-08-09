package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun BeeHealthyNavHost() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = authGraphRoute
    ) {
        authGraph(navHostController)
    }
}