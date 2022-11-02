package com.cornellappdev.scoop.ui.onboarding

import OnboardingProfile
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cornellappdev.scoop.onboarding.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingHolderView() {

    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(
            count = 6,
            state = pagerState,
        ) { page ->
            // Our page content
            when (page) {
                0 -> OnboardingView2(pagerState)
                1 -> OnboardingView3(pagerState)
                2 -> OnboardingView3Half(pagerState)
                3 -> OnboardingView4(pagerState)
                4 -> OnboardingView5(pagerState)
                5 -> OnboardingProfile(pagerState)
            }
        }
    }
}



