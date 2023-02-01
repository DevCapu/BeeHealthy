package br.com.devcapu.beehealthy.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.devcapu.beehealthy.screen.loginGraphRoute
import br.com.devcapu.beehealthy.screen.loginScreen


const val authGraphRoute = "auth"

fun NavGraphBuilder.authGraph(
    onNavigateToRegister: () -> Unit
) {
    navigation(
        startDestination = loginGraphRoute,
        route = authGraphRoute
    ) {
        loginScreen(onNavigateToRegister)
        registerGraph()
    }
}