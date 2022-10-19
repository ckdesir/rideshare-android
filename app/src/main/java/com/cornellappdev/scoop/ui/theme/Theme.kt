package com.cornellappdev.scoop.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ScoopTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Green
    )

    MaterialTheme(
        colors = colorPalette,
        typography = typography,
        content = content
    )
}