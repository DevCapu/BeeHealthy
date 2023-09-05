package br.com.devcapu.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.text.TextStyle
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
    label: @Composable ()  -> Unit = { },
    trailingIcon: @Composable () -> Unit = { },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    options: KeyboardOptions = KeyboardOptions.Default,
    actions: KeyboardActions = KeyboardActions.Default,
) = Column(modifier = modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        label = label,
        isError = isShowingError,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = options,
        keyboardActions = actions,
        textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
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
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun OutlineInputError() {
    var value by remember { mutableStateOf("") }
    OutlineInput(
        value = value,
        onValueChange = { value = it },
        errorMessage = "Cannot be empty",
        isShowingError = true,
    )
}

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OutlineInputPreview() {
    OutlineInput(
        value = "",
        onValueChange = { },
    )
}