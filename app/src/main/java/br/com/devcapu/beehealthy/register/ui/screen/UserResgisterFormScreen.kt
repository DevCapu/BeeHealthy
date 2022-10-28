package br.com.devcapu.beehealthy.register.ui.screen

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
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.common.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.register.ui.state.RegisterUIState
import br.com.devcapu.beehealthy.register.ui.RegisterViewModel

@Composable
fun UserRegisterScreen(viewModel: RegisterViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    UserRegisterScreen(state)
}

@Composable
fun UserRegisterScreen(state: RegisterUIState) =
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
            onClick = state.onGoToNextStep,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) { Text(text = stringResource(id = R.string.next_step)) }
    }


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserRegisterScreenPreview() {
    UserRegisterScreen(RegisterUIState())
}
