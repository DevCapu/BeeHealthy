package br.com.devcapu.beehealthy.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import br.com.devcapu.beehealthy.screen.auth.login.LOGIN_ROUTE
import br.com.devcapu.beehealthy.screen.auth.login.loginScreen
import br.com.devcapu.beehealthy.screen.auth.login.navigateToLogin
import br.com.devcapu.beehealthy.screen.auth.register.navigateToActivityLevelSelection
import br.com.devcapu.beehealthy.screen.auth.register.navigateToBasicInfo
import br.com.devcapu.beehealthy.screen.auth.register.navigateToGenderSelection
import br.com.devcapu.beehealthy.screen.auth.register.navigateToObjectiveSelection

const val AUTH_GRAPH_ROUTE = "AUTH_GRAPH_ROUTE"

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = LOGIN_ROUTE,
        route = AUTH_GRAPH_ROUTE
    ) {
        loginScreen(
            onNavigateToRegisterScreen = { navController.navigateToRegisterGraph() },
            onNavigateToHome = { navController.navigateToHomeScreen() }
        )

        registerGraph(
            onGoToLogin = { navController.navigateToLogin() },
            onGoToUserInfoRegistration = { navController.navigateToBasicInfo() },
            onGoToUserGenderScreen = { navController.navigateToGenderSelection() },
            onGoToObjectiveScreen = { navController.navigateToObjectiveSelection() },
            onGoToActivityScreen = { navController.navigateToActivityLevelSelection() },
            onFinish = { navController.navigateToHomeScreen() }
        )
    }
}