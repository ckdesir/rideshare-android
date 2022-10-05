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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.RightArrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView3(pagerState: PagerState) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OnboardingHeader(pagerState = pagerState, "About You")
            Spacer(modifier = Modifier.weight(1F))
            Text(
                fontFamily = FontFamily.Default,
                text = "What’s you preferred method of contact?",
                fontSize = 19.sp,
            )
            methodButtons()
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 30.dp)

            ) {
                RightArrow(pagerState)
            }
            Spacer(
                modifier = Modifier
                    .weight(1.5F)
            )
        }

    }
}

@Composable
fun methodButtons() {
    var selected by remember { mutableStateOf("email") }

    Column(
        modifier = Modifier
            .padding(
                start = 40.dp
            )
            .fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            RadioButton(
                selected = selected == "email",
                onClick = { selected = "email" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = androidx.compose.ui.graphics.Color.Black,
                    unselectedColor = androidx.compose.ui.graphics.Color.Gray,
                )
            )
            Text(
                text = "Cornell Email",
                modifier = Modifier
                    .clickable(onClick = { selected = "email" })
                    .padding(start = 2.dp),
                fontSize = 15.sp,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selected == "phone",
                onClick = { selected = "phone" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = androidx.compose.ui.graphics.Color.Black,
                    unselectedColor = androidx.compose.ui.graphics.Color.Gray,
                )
            )
            Text(
                text = "Phone Number",
                modifier = Modifier
                    .clickable(onClick = { selected = "phone" })
                    .padding(start = 2.dp),
                fontSize = 15.sp,
            )

        }

    }


}


//@Preview
//@Composable
//fun OnboardingPreview(){
//    OnboardingView3(pagerState)
//}