package com.cornellappdev.scoop.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

private val defaultTypography = Typography()
val typography = Typography(
    defaultFontFamily = FontFamily.SansSerif,
    h5 = defaultTypography.h5.copy(fontSize = 22.sp, color = Color.Black),
    subtitle1 = defaultTypography.subtitle1.copy(fontSize = 18.sp, color = Color.Black)
)