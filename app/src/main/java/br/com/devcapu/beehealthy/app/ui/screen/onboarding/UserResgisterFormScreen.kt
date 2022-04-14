package br.com.devcapu.beehealthy.app.ui.screen.onboarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.devcapu.beehealthy.R
import br.com.devcapu.beehealthy.app.ui.component.FormWithBeeHealthIdentity
import br.com.devcapu.beehealthy.app.ui.viewModel.RegisterViewModel

@Composable
fun UserRegisterFormScreen(viewModel: RegisterViewModel) {
    HealthRegisterContent(
        name = viewModel.name,
        onNameChange = { viewModel.name = it },
        age = viewModel.age,
        onAgeChange = { viewModel.age = it },
        height = viewModel.height,
        onHeightChange = { viewModel.height = it },
        weight = viewModel.weight,
        onWeightChange = { viewModel.weight = it },
        finishSignUp = {
            viewModel.goTo(OnboardSteps.BIOLOGICAL_GENDER_SELECTION.name)
        }
    )
}

@Composable
fun HealthRegisterContent(
    name: String,
    onNameChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    height: String,
    onHeightChange: (String) -> Unit,
    weight: String,
    onWeightChange: (String) -> Unit,
    finishSignUp: () -> Unit,
) {
    FormWithBeeHealthIdentity(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        val defaultModifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text(text = stringResource(R.string.name_label)) },
            modifier = defaultModifier
        )

        OutlinedTextField(
            value = age,
            onValueChange = onAgeChange,
            placeholder = { Text(text = stringResource(R.string.age_label)) },
            modifier = defaultModifier
        )

        OutlinedTextField(
            value = height,
            onValueChange = onHeightChange,
            placeholder = { Text(text = stringResource(R.string.height_label)) },
            modifier = defaultModifier
        )

        OutlinedTextField(
            value = weight,
            onValueChange = onWeightChange,
            placeholder = { Text(text = stringResource(R.string.weight_label)) },
            modifier = defaultModifier
        )

        Button(
            onClick = finishSignUp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) { Text(text = stringResource(id = R.string.next_step)) }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HealthRegisterScreenPreview() {
    HealthRegisterContent(
        name = "",
        onNameChange = {},
        weight = "",
        onWeightChange = {},
        height = "",
        onHeightChange = {},
        age = "",
        onAgeChange = {},
        finishSignUp = {}
    )
}
