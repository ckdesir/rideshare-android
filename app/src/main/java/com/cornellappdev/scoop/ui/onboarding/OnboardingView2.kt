package com.cornellappdev.scoop.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.components.BackArrow
import com.cornellappdev.scoop.components.RightArrow
import com.cornellappdev.scoop.onboarding.OnboardingFooter
import com.cornellappdev.scoop.onboarding.NavHeader
import com.cornellappdev.scoop.ui.components.general.DenseTextField
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView2(pagerState: PagerState) {

    val (nameText, setNameText) = rememberSaveable { mutableStateOf("") }
    val (pronounsText, setPronounsText) = rememberSaveable { mutableStateOf("") }
    val (hometownText, setHometownText) = rememberSaveable { mutableStateOf("") }
    val (yearText, setYearText) = rememberSaveable { mutableStateOf("") }

    var isComplete = nameText != "" && pronounsText != "" && hometownText != "" && yearText != ""

    Column(
        Modifier.background(Color.White)
    ) {
        NavHeader(pagerState = pagerState, title = "About You")

        Column(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
            ) {

                Spacer(modifier = Modifier.height(70.dp))

                Box() {
                    Column(Modifier.fillMaxWidth()) {
                        DenseTextField(
                            label="Name",
                            value = nameText,
                            setValue = setNameText,
                            placeholderText = "Enter Name"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        DenseTextField(
                            label="Pronouns",
                            value = pronounsText,
                            setValue = setPronounsText,
                            placeholderText = "Enter Pronouns"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        DenseTextField(
                            label="Hometown",
                            value = hometownText,
                            setValue = setHometownText,
                            placeholderText = "Enter Hometown"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        DenseTextField(
                            modifier = Modifier.padding(
                                bottom = 10.dp
                            ),
                            label="Year",
                            value = yearText,
                            setValue = setYearText,
                            placeholderText = "Enter Class Year"
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement  =  Arrangement.SpaceBetween
            ) {
                BackArrow(pagerState)
                RightArrow(pagerState, isComplete)
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


//@Preview
//@Composable
//fun PreviewOnboard(){
//    OnboardingView2(pagerState)
//}