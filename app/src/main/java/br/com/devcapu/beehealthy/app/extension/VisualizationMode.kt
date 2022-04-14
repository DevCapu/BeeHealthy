package br.com.devcapu.beehealthy.app.extension

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun VisualTransformation.Companion.visualizationMode(showPassword: Boolean) = if (showPassword) {
    None
} else {
    PasswordVisualTransformation()
}