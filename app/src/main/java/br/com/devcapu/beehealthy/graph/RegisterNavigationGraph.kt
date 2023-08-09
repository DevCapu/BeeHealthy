package br.com.devcapu.beehealthy.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.devcapu.beehealthy.screen.auth.register.credentialScreenRoute
import br.com.devcapu.beehealthy.screen.auth.register.credentialsScreen
import br.com.devcapu.beehealthy.screen.auth.register.userBasicInfoScreen

const val registerGraphRoute = "register"

fun NavGraphBuilder.registerGraph(
    onGoToUserInfoRegistration: () -> Unit = { },
    onGoToLogin: () -> Unit = { }
) {
    navigation(
        startDestination = credentialScreenRoute,
        route = registerGraphRoute
    ) {
        credentialsScreen(
            onGoToNextScreen = onGoToUserInfoRegistration,
            onGoToLogin = onGoToLogin
        )

        userBasicInfoScreen(onGoToLogin)
    }
}

fun NavController.navigateToRegisterGraph() {
    navigate(registerGraphRoute)
}