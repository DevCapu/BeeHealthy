package br.com.devcapu.beehealthy.authentication.register.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.authentication.register.ui.RegistrationScreen.*
import br.com.devcapu.beehealthy.authentication.register.ui.RegistrationScreen.UserInfoScreen
import br.com.devcapu.beehealthy.authentication.register.ui.screen.*

sealed class RegistrationScreen(val route: String) {
    object AuthenticationScreen : RegistrationScreen("auth_screen")
    object UserInfoScreen : RegistrationScreen("user_info_screen")
    object BiologicalGenderScreen : RegistrationScreen("biological_gender_selection_screen")
    object ObjectiveScreen : RegistrationScreen("objective_selection_screen")
    object ActivityLevelScreen : RegistrationScreen("activity_level_selection_screen")
}

@Composable
fun RegistrationNavGraph(
    viewModel: RegisterViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AuthenticationScreen.route) {
        composable(AuthenticationScreen.route) {
            AuthRegisterScreen(viewModel) { navController.navigate(UserInfoScreen.route) }
        }
        composable(UserInfoScreen.route) {
            UserRegisterScreen(viewModel) { navController.navigate(BiologicalGenderScreen.route) }
        }
        composable(BiologicalGenderScreen.route) {
            GenderSelectionScreen(viewModel) { navController.navigate(ObjectiveScreen.route) }
        }
        composable(ObjectiveScreen.route) {
            ObjectiveSelectionScreen(viewModel) { navController.navigate(ActivityLevelScreen.route) }
        }
        composable(ActivityLevelScreen.route) {
            ActivityLevelSelectionScreen(viewModel) { viewModel.savePatient() }
        }
    }
}