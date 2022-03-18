package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import com.cornellappdev.scoop.components.DenseTextField

@Composable
fun OnboardingView2(){
    var nameText by remember { mutableStateOf("text") }
    var pronounsText by remember { mutableStateOf("text") }
    var homeTownText by remember { mutableStateOf("text") }
    var classYearText by remember { mutableStateOf("text") }

    Scaffold() {

        Column() {

//            DenseTextField(value = nameText, setValue = nameText, placeholderText = "Hello")

        }
    }
}