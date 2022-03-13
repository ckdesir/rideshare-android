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
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun PostBody(onPostNewTrip: () -> Unit) {
    val pagerState = rememberPagerState(1)
    val coroutineScope = rememberCoroutineScope()

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
                1 -> SecondPage(proceedToPageIndex(coroutineScope, page + 1, pagerState))
            }
        }

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

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 40.dp),
            activeColor = DarkGray
        )
    }
}

/**
 * Proceeds to the specified pageIndex
 */
@OptIn(ExperimentalPagerApi::class)
fun proceedToPageIndex(
    coroutineScope: CoroutineScope,
    pageIndex: Int,
    pagerState: PagerState
): () -> Unit {
    return {
        coroutineScope.launch {
            pagerState.scrollToPage(pageIndex)
        }
    }
}



