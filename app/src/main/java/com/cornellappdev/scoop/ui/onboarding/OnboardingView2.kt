package com.cornellappdev.scoop.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.RightArrow
import com.cornellappdev.scoop.onboarding.OnboardingFooter
import com.cornellappdev.scoop.onboarding.OnboardingHeader
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

    Column(
        Modifier.background(Color.White)
    ) {
        OnboardingHeader(pagerState = pagerState, "About You")

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

                Spacer(modifier = Modifier.height(30.dp))

                Box() {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            text = "Name",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(
                                bottom = 10.dp
                            )
                        )
                        DenseTextField(
                            value = nameText,
                            setValue = setNameText,
                            placeholderText = "Enter Name"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            text = "Pronouns",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(
                                bottom = 10.dp
                            )
                        )
                        DenseTextField(
                            value = pronounsText,
                            setValue = setPronounsText,
                            placeholderText = "Enter Pronouns"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            text = "Name",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(
                                bottom = 10.dp
                            )
                        )
                        DenseTextField(
                            value = hometownText,
                            setValue = setHometownText,
                            placeholderText = "Enter Hometown"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            text = "Class Year",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(
                                bottom = 10.dp
                            )
                        )
                        DenseTextField(
                            modifier = Modifier.padding(
                                bottom = 10.dp
                            ),
                            value = yearText,
                            setValue = setYearText,
                            placeholderText = "Enter Class Year"
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(20.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                ) {

                    Spacer(modifier = Modifier.height(30.dp))

                    Box(){
                        Column(Modifier.fillMaxWidth(),) {
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                text = "Name",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(
                                    bottom = 10.dp
                                )
                            )
                            DenseTextField(value = nameText, setValue = setNameText, placeholderText = "Enter Name")
                        }
                    }

                    Box(Modifier.fillMaxWidth()){
                        Column() {
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                text = "Pronouns",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(
                                    bottom = 10.dp
                                )
                            )
                            DenseTextField(value = pronounsText, setValue = setPronounsText, placeholderText = "Enter Pronouns")
                        }
                    }

                    Box(Modifier.fillMaxWidth()){
                        Column() {
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                text = "Name",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(
                                    bottom = 10.dp
                                )
                            )
                            DenseTextField(value = hometownText, setValue = setHometownText, placeholderText = "Enter Hometown")
                        }
                    }

                    Box(Modifier.fillMaxWidth()){
                        Column() {
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                text = "Class Year",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(
                                    bottom = 10.dp
                                )
                            )
                            DenseTextField(value = yearText, setValue = setYearText, placeholderText = "Enter Class Year")
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(20.dp)
                ){
                    RightArrow(pagerState)
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row (
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                ){
                    OnboardingFooter(carIndex = pagerState.currentPage)
                }
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