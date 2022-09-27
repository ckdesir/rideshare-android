package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.ui.Modifier

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingHolderView(){

    val pagerState = rememberPagerState()

    Scaffold(Modifier.fillMaxHeight()) {

        Column(modifier = Modifier.fillMaxSize()) {

            HorizontalPager(count = 5, state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                // Our page content
                when(page){
                    0-> OnboardingView2(pagerState)
                    1-> OnboardingView3(pagerState)
                    2-> OnboardingView3Half(pagerState)
                    3-> OnboardingView4(pagerState)
                    4-> OnboardingView5(pagerState)
                }
            }
        }
    }

}

