package br.com.devcapu.beehealthy.graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.devcapu.beehealthy.screen.*
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel

sealed class RegistrationScreen(val route: String) {
    object AuthenticationScreen : RegistrationScreen("auth_screen")
    object UserInfoScreen : RegistrationScreen("user_info_screen")
    object BiologicalGenderScreen : RegistrationScreen("biological_gender_selection_screen")
    object ObjectiveScreen : RegistrationScreen("objective_selection_screen")
    object ActivityLevelScreen : RegistrationScreen("activity_level_selection_screen")
}

@ExperimentalMaterialApi
@Composable
fun RegistrationNavGraph(
    viewModel: RegisterViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RegistrationScreen.AuthenticationScreen.route) {
        composable(RegistrationScreen.AuthenticationScreen.route) {
            AuthRegisterScreen(viewModel) { navController.navigate(RegistrationScreen.UserInfoScreen.route) }
        }
        composable(RegistrationScreen.UserInfoScreen.route) {
            UserRegisterScreen(viewModel) { navController.navigate(RegistrationScreen.BiologicalGenderScreen.route) }
        }
        composable(RegistrationScreen.BiologicalGenderScreen.route) {
            GenderSelectionScreen(viewModel) { navController.navigate(RegistrationScreen.ObjectiveScreen.route) }
        }
        composable(RegistrationScreen.ObjectiveScreen.route) {
            ObjectiveSelectionScreen(viewModel) { navController.navigate(RegistrationScreen.ActivityLevelScreen.route) }
        }
        composable(RegistrationScreen.ActivityLevelScreen.route) {
            ActivityLevelSelectionScreen(viewModel) { viewModel.savePatient() }
        }
    }
}