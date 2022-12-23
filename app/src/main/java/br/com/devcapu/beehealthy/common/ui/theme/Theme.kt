package br.com.devcapu.beehealthy.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = Primary,
    primaryVariant =  PrimaryVariant,
    secondary =  Secondary,
    secondaryVariant = SecondaryVariant,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = onSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant =  PrimaryVariant,
    secondary =  Secondary,
    secondaryVariant = SecondaryVariant,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = onSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
)

@Composable
fun BeeHealthyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}