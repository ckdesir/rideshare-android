package com.cornellappdev.scoop.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.RightArrow
import com.cornellappdev.scoop.ui.components.general.UnderlinedEditText
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView2(pagerState: PagerState) {

    val (nameText, setNameText) = rememberSaveable { mutableStateOf("") }
    val (pronounsText, setPronounsText) = rememberSaveable { mutableStateOf("") }
    val (hometownText, setHometownText) = rememberSaveable { mutableStateOf("") }
    val (yearText, setYearText) = rememberSaveable { mutableStateOf("") }

    Scaffold(

        modifier = Modifier
            .padding(
                start = 40.dp,
                end = 40.dp,
            )
            .fillMaxWidth()
    ) {

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            OnboardingHeader(pagerState = pagerState, "About You")
            Spacer(modifier = Modifier.weight(1F))

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
                    UnderlinedEditText(
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
                    UnderlinedEditText(
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
                    UnderlinedEditText(
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
                    UnderlinedEditText(
                        value = yearText,
                        setValue = setYearText,
                        placeholderText = "Enter Class Year"
                    )
                }

            }

            Box(

                modifier = Modifier.align(Alignment.End)
            ) {
                RightArrow(pagerState)
            }

            Spacer(modifier = Modifier.weight(1.5F))

        }
    }
}


//@Preview
//@Composable
//fun PreviewOnboard(){
//    OnboardingView2(pagerState)
//}