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
fun OnboardingView5(pagerState: PagerState) {

    val (snackText, setSnackText) = rememberSaveable { mutableStateOf("") }
    val (songText, setSongText) = rememberSaveable { mutableStateOf("") }
    val (stopText, setStopText) = rememberSaveable { mutableStateOf("") }

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
            OnboardingHeader(pagerState = pagerState, "Favorites")
            Spacer(modifier = Modifier.weight(1F))

            Box(){
                Column(Modifier.fillMaxWidth(),) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        text = "Roadtrip Snack",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = snackText, setValue = setSnackText, placeholderText = "enter snack")
                }

            }

            Box(Modifier.fillMaxWidth()){
                Column() {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        text = "Roadtrip Song",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = songText, setValue = setSongText, placeholderText = "enter song")
                }

            }

            Box(Modifier.fillMaxWidth()){

                Column() {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        text = "Roadtrip Stop",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = stopText, setValue = setStopText, placeholderText = "enter stop")
                }

            }

            Box(

                modifier = Modifier.align(Alignment.End)
            ){
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