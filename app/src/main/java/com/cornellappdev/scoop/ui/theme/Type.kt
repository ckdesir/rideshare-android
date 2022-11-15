package com.cornellappdev.scoop.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R

private val defaultTypography = Typography()

val Rambla = FontFamily(
    Font(R.font.rambla_regular),
    Font(R.font.rambla_bold, FontWeight.Bold)
)

val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_medium, FontWeight.Medium)
)

val typography = Typography(
    defaultFontFamily = FontFamily.SansSerif,
    h5 = defaultTypography.h5.copy(fontSize = 22.sp, color = Color.Black),
    subtitle1 = defaultTypography.subtitle1.copy(fontSize = 18.sp, color = Color.Black),
    subtitle2 = TextStyle(fontFamily = Rambla, fontSize = 16.sp, color = Color.Black),
    body1 = TextStyle(fontFamily = Roboto, fontSize = 16.sp, color = Color.Black)
)