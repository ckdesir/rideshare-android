package com.cornellappdev.scoop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.cornellappdev.scoop.ui.navigation.MainScreen
import com.cornellappdev.scoop.ui.theme.ScoopTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalPagerApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScoopTheme {
                MainScreen()
            }
        }
    }
}