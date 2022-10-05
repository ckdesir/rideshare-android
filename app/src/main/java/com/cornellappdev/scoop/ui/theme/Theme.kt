package com.cornellappdev.scoop.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ScoopTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colorPalette,
<<<<<<< HEAD
        typography = typography,
=======
        typography = Typography,
>>>>>>> ab65d5d468cb32191ba9480cf4e229ae24d4f11b
        content = content
    )
}