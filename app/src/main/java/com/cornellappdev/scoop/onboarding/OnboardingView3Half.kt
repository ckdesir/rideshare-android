package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.DenseTextField
import com.cornellappdev.scoop.components.RightArrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView3Half(pagerState: PagerState) {

    val (phoneText, setPhoneText) = rememberSaveable { mutableStateOf("") }

    Scaffold(

    ) {

        Column() {

            OnboardingHeader(pagerState = pagerState, "About You")

            Column(
                modifier = Modifier
                    .padding(
                        start = 40.dp,
                        end = 40.dp,
                    )
                    .fillMaxWidth()
            ) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                ) {

                    Spacer(modifier = Modifier.height(150.dp))

                    Box(){
                        Column(Modifier.fillMaxWidth(),) {
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                text = "Phone number",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(
                                    bottom = 10.dp
                                )
                            )
                            DenseTextField(value = phoneText, setValue = setPhoneText, placeholderText = "000-000-0000")
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
        }
    }
}


//@Preview
//@Composable
//fun PreviewOnboard(){
//    OnboardingView2(pagerState)
//}