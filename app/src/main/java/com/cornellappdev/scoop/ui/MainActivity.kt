package com.cornellappdev.scoop.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.cornellappdev.scoop.ui.components.general.CalendarScreen
import com.cornellappdev.scoop.ui.theme.ScoopTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.Month
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets {
                ScoopTheme {
                    CalendarScreen(onBackPressed = { finish() })
                }
            }
        }
    }
}



