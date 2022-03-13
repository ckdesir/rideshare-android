package com.cornellappdev.scoop.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.components.SecondPage
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.LightGray
import com.google.accompanist.pager.*

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun PostBody(onPostNewTrip: () -> Unit) {
    val pagerState = rememberPagerState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = 3, state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), userScrollEnabled = false
        ) { page ->
            when (page) {
                1 -> SecondPage(GoToNext(page + 1, pagerState))
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 40.dp),
            activeColor = DarkGray
        )

        AnimatedVisibility(visible = pagerState.currentPage == 2) {
            Button(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), onClick = onPostNewTrip,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = LightGray,
                    contentColor = Color.Black
                )
            ) {
                Text(text = stringResource(R.string.post_trip))
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun GoToNext(pageIndex: Int, pagerState: PagerState) {
    LaunchedEffect(key1 = Unit, block = {
        pagerState.scrollToPage(pageIndex)
    })
}



