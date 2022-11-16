package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.BackArrow
import com.cornellappdev.scoop.components.RightArrow
import com.cornellappdev.scoop.ui.components.general.DenseTextField
import com.cornellappdev.scoop.ui.components.general.UnderlinedEditText
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView5(pagerState: PagerState) {

    val (snackText, setSnackText) = rememberSaveable { mutableStateOf("") }
    val (songText, setSongText) = rememberSaveable { mutableStateOf("") }
    val (stopText, setStopText) = rememberSaveable { mutableStateOf("") }

    var isComplete = stopText != "" && songText != "" && snackText != ""

            Column(
        Modifier.background(Color.White)
    ) {
        OnboardingHeader(pagerState = pagerState, title = "Favorites")

        Column(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                )
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Spacer(modifier = Modifier.height(90.dp))

                Box() {
                    Column(Modifier.fillMaxWidth()) {
                        DenseTextField(
                            label="Roadtrip Snack",
                            value = snackText,
                            setValue = setSnackText,
                            placeholderText = "enter snack"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {
                    Column() {
                        DenseTextField(
                            label="Roadtrip Song",
                            value = songText,
                            setValue = setSongText,
                            placeholderText = "enter song"
                        )
                    }
                }

                Box(Modifier.fillMaxWidth()) {

                    Column() {
                        DenseTextField(
                            label="Stop",
                            value = stopText,
                            setValue = setStopText,
                            placeholderText = "enter stop"
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