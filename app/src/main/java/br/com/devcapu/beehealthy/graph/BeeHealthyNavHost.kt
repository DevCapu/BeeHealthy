package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun BeeHealthyNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = authGraphRoute
    ) {
        authGraph { navHostController.navigateToRegisterGraph() }
    }

}