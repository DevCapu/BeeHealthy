package br.com.devcapu.beehealthy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R

val PrimaryFont = FontFamily(
    Font(resId = R.font.racing_sans_one, style = FontStyle.Normal),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = PrimaryFont,
        fontSize = 32.sp,
        fontWeight = Bold,
        color = Primary
    ),
    h2 = TextStyle(
        fontFamily = PrimaryFont,
        fontSize = 16.sp,
        fontWeight = SemiBold,
    )
)