package com.cornellappdev.scoop.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.BackArrow
import com.cornellappdev.scoop.components.RightArrow
import com.cornellappdev.scoop.onboarding.OnboardingFooter
import com.cornellappdev.scoop.onboarding.OnboardingHeader
import com.cornellappdev.scoop.ui.components.general.DenseTextField
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView3(pagerState: PagerState) {

    Column(
        Modifier.background(Color.White)
    ) {
        OnboardingHeader(pagerState = pagerState, title = "About You")

        Column(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                )
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.height(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    fontFamily = FontFamily.Default,
                    text = "PREFERRED CONTACT METHOD",
                    fontSize = 16.sp,
                )
                MethodButtons()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement  =  Arrangement.SpaceBetween
            ) {
                BackArrow(pagerState)
                RightArrow(pagerState, true)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
            ) {
                OnboardingFooter(carIndex = pagerState.currentPage)
            }
        }
    }

}


@Composable
fun MethodButtons() {
    var selected by remember { mutableStateOf("email") }
    val (phoneText, setPhoneText) = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(
                start = 50.dp
            )
            .fillMaxWidth(),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            RadioButton(
                selected = selected == "email",
                onClick = { selected = "email" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Black,
                    unselectedColor = Color.Gray,
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
                    selectedColor = Color.Black,
                    unselectedColor = Color.Gray,
                )
            )
            Text(
                text = "Phone",
                modifier = Modifier
                    .clickable(onClick = { selected = "phone" })
                    .padding(start = 2.dp),
                fontSize = 15.sp,
            )
        }

        if(selected == "phone"){

            Row(
                modifier =
                    Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
            ) {
                DenseTextField(
                    label="Phone",
                    value = phoneText,
                    setValue = setPhoneText,
                    placeholderText = "000-000-0000",
                    phoneNumber = true
                )
            }

        }
    }
}
