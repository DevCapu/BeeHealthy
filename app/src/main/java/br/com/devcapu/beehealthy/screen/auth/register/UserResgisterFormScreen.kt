package br.com.devcapu.beehealthy.screen.auth.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.uistate.RegisterUIState
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel
import br.com.devcapu.beehealthy.viewmodel.RegisterViewModel.Companion.Factory

private const val USER_BASIC_INFO_SCREEN_ROUTE = "USER_BASIC_INFO_SCREEN_ROUTE"

fun NavController.navigateToBasicInfo() = navigate(USER_BASIC_INFO_SCREEN_ROUTE)

fun NavGraphBuilder.userBasicInfoScreen(onClickNextStep: () -> Unit) {
    composable(route = USER_BASIC_INFO_SCREEN_ROUTE) {
        val viewModel: RegisterViewModel = viewModel(factory = Factory)
        val uiState by viewModel.uiState.collectAsState()

        UserRegisterScreen(
            state = uiState,
            onClickNextStep = onClickNextStep
        )
    }
}

@Composable
fun UserRegisterScreen(
    state: RegisterUIState,
    onClickNextStep: () -> Unit
) =
    FormWithBeeHealthIdentity {
        val defaultModifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()

        OutlinedTextField(
            value = state.name,
            onValueChange = state.onNameChanged,
            placeholder = { Text(text = stringResource(R.string.name_label)) },
            modifier = defaultModifier
        )

        OutlinedTextField(
            value = state.birthday,
            onValueChange = state.onBirthdayChanged,
            placeholder = { Text(text = stringResource(R.string.age_label)) },
            modifier = defaultModifier
        )

        OutlinedTextField(
            value = state.height,
            onValueChange = state.onHeightChanged,
            placeholder = { Text(text = stringResource(R.string.height_label)) },
            modifier = defaultModifier
        )

        OutlinedTextField(
            value = state.weight,
            onValueChange = state.onWeightChanged,
            placeholder = { Text(text = stringResource(R.string.weight_label)) },
            modifier = defaultModifier
        )

        Button(
            onClick = onClickNextStep,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) { Text(text = stringResource(id = R.string.next_step)) }
    }


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserRegisterScreenPreview() {
    UserRegisterScreen(RegisterUIState()) { }
}
