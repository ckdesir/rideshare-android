package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.components.RightArrow
import android.graphics.Color as Color1

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
            methodButtons()
            Box(
                modifier = Modifier.align(Alignment.End)
            ){
                RightArrow()
            }
            Spacer(modifier = Modifier.weight(1F)
                .padding(
                    end = 20.dp

            ))

        }

    }
}

@Composable
fun methodButtons(){
    var selected by remember { mutableStateOf("email") }

    Column() {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected == "email",
                onClick = { selected = "email"},
                colors = RadioButtonDefaults.colors(
                    selectedColor = androidx.compose.ui.graphics.Color.Black,
                    unselectedColor = androidx.compose.ui.graphics.Color.Gray,
                ))
            Text(
                text = "Cornell Email",
                modifier = Modifier
                    .clickable(onClick = { selected = "email" })
                    .padding(start = 2.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = selected == "phone", onClick = { selected = "phone" })
            Text(
                text = "Phone Number",
                modifier = Modifier
                    .clickable(onClick = { selected = "phone" })
                    .padding(start = 2.dp)
            )

        }

    }




}


@Preview
@Composable
fun OnboardingPreview(){
    OnboardingView3()
}