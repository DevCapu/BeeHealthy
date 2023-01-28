package br.com.devcapu.beehealthy.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.devcapu.beehealthy.theme.BeeHealthyTheme

@Composable
fun PasswordTrailingIcon(showPassword: Boolean, onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        if (showPassword) {
            Icon(imageVector = Icons.Outlined.Visibility,
                contentDescription = "Eye open")
        } else {
            Icon(imageVector = Icons.Outlined.VisibilityOff,
                contentDescription = "Eye open")
        }
    }
}

@Preview(name = "Showing password")
@Composable
fun PasswordWithTrailingIcon() {
    BeeHealthyTheme {
        PasswordTrailingIcon(showPassword = true) { }
    }
}

@Preview(name = "Hiding password")
@Composable
fun HiddingPasswordWithTrailingIcon() {
    BeeHealthyTheme {
        PasswordTrailingIcon(showPassword = false) { }
    }
}