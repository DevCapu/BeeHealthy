package br.com.devcapu.beehealthy.common.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlineInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    isShowingError: Boolean = false,
    placeholder: @Composable () -> Unit = { },
    trailingIcon: @Composable () -> Unit = { },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    options: KeyboardOptions = KeyboardOptions.Default,
    actions: KeyboardActions = KeyboardActions.Default
) = Column(
    modifier = modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        isError = isShowingError,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = options,
        keyboardActions = actions
    )

    if (isShowingError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineInputError() {
    var value by remember { mutableStateOf("") }
    OutlineInput(
        value = value,
        onValueChange = { value = it },
        errorMessage = "Cannot be empty",
        isShowingError = true,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun OutlineInput() {
    var value by remember { mutableStateOf("") }
    OutlineInput(
        value = value,
        onValueChange = { value = it },
        isShowingError = false,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    )
}