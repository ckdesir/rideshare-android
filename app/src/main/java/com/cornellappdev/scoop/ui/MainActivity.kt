package com.cornellappdev.scoop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cornellappdev.scoop.ui.navigation.MainScreen
import com.cornellappdev.scoop.ui.theme.ScoopTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets {
                ScoopTheme {
                    MainScreen()
                }
            }
        }
    }
}

