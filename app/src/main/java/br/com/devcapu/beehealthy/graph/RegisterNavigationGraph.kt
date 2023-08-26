package br.com.devcapu.beehealthy.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.devcapu.beehealthy.screen.auth.register.CREDENTIALS_SCREEN_ROUTE
import br.com.devcapu.beehealthy.screen.auth.register.activityLevelSelectionScreen
import br.com.devcapu.beehealthy.screen.auth.register.credentialsScreen
import br.com.devcapu.beehealthy.screen.auth.register.genderSelectionScreen
import br.com.devcapu.beehealthy.screen.auth.register.objectiveSelectionScreen
import br.com.devcapu.beehealthy.screen.auth.register.userBasicInfoScreen

const val REGISTER_GRAPH_ROUTE = "REGISTER_GRAPH_ROUTE"

fun NavGraphBuilder.registerGraph(
    onGoToUserInfoRegistration: () -> Unit = { },
    onGoToUserGenderScreen: () -> Unit = { },
    onGoToObjectiveScreen: () -> Unit = { },
    onGoToActivityScreen: () -> Unit = { },
    onGoToLogin: () -> Unit = { },
    onFinish: () -> Unit = {}
) {
    navigation(
        startDestination = CREDENTIALS_SCREEN_ROUTE,
        route = REGISTER_GRAPH_ROUTE
    ) {
        credentialsScreen(onClickNextStep = onGoToUserInfoRegistration, onGoToLogin = onGoToLogin)
        userBasicInfoScreen(onClickNextStep = onGoToUserGenderScreen)
        genderSelectionScreen(onClickNextStep = onGoToObjectiveScreen)
        objectiveSelectionScreen(onClickNextStep = onGoToActivityScreen)
        activityLevelSelectionScreen(onClickNextStep = onFinish)
    }
}

fun NavController.navigateToRegisterGraph() {
    navigate(REGISTER_GRAPH_ROUTE)
}