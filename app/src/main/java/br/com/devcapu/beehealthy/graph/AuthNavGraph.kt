package br.com.devcapu.beehealthy.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import br.com.devcapu.beehealthy.screen.auth.login.loginGraphRoute
import br.com.devcapu.beehealthy.screen.auth.login.loginScreen
import br.com.devcapu.beehealthy.screen.auth.login.navigateToLogin
import br.com.devcapu.beehealthy.screen.auth.register.navigateToBasicInfoScreen


const val authGraphRoute = "auth"

fun NavGraphBuilder.authGraph(
   navController: NavHostController
) {
    navigation(
        startDestination = loginGraphRoute,
        route = authGraphRoute
    ) {
        loginScreen { navController.navigateToRegisterGraph()}

        registerGraph(
            onGoToLogin = { navController.navigateToLogin() },
            onGoToUserInfoRegistration = { navController.navigateToBasicInfoScreen() }
        )
    }
}