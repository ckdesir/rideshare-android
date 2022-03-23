package com.cornellappdev.scoop.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingHolderView(){
    HorizontalPager(count = 10) { page ->
        // Our page content

        when(page){

            0-> OnboardingView1()
            1-> OnboardingView2()
            2-> OnboardingView3()

        }


    }
}

