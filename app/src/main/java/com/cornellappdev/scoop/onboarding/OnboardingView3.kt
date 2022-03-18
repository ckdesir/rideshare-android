package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.components.RightArrow

@Composable
fun OnboardingView3 (){
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = Modifier.weight(1F))
            Text(
                fontFamily = FontFamily.Default,
                text = "What’s you preferred method of contact?"
            )
            Spacer(modifier = Modifier.weight(1F))

        }
    }
}

@Preview
@Composable
fun OnboardingPreview(){
    OnboardingView3()
}