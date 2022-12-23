package br.com.devcapu.beehealthy.common.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.sp
import br.com.devcapu.beehealthy.R

val Carme = FontFamily(
    Font(resId = R.font.carme, style = FontStyle.Normal),
)

val PrimaryFont = Carme

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Carme,
        fontSize = 32.sp,
        fontWeight = Bold,
    ),
    h2 = TextStyle(
        fontFamily = Carme,
        fontSize = 16.sp,
        fontWeight = SemiBold,
    ),
    h3 = TextStyle(
        fontFamily = Carme,
        fontSize = 16.sp,
        fontWeight = Normal,
    )
)