package br.com.devcapu.beehealthy.common.ui.extension

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun VisualTransformation.Companion.visualizationMode(showPassword: Boolean) = if (showPassword) {
    None
} else {
    PasswordVisualTransformation()
}