package com.cornellappdev.scoop.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingHolderView(){

    val pagerState = rememberPagerState()

    HorizontalPager(count = 10, state = pagerState) { page ->
        // Our page content

        when(page){
            0-> OnboardingView1(pagerState)
            1-> OnboardingView2(pagerState)
            2-> OnboardingView3(pagerState)
            3-> OnboardingView4(pagerState)
            4-> OnboardingView5(pagerState)

        }


    }
}

