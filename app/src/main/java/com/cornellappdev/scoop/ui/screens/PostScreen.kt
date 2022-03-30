package com.cornellappdev.scoop.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.components.post.SecondPage
import com.cornellappdev.scoop.ui.components.post.ThirdPage
import com.cornellappdev.scoop.models.Trip
import com.cornellappdev.scoop.ui.components.post.FirstPage
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.LightGray
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun PostScreen(onPostNewTrip: (Trip) -> Unit) {
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
    val tripState = remember {
        mutableStateOf(
            Trip()
        )
    }

    Box {
        Column {
            HorizontalPager(
                count = 3, state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), userScrollEnabled = false
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.trip_details),
                        style = TextStyle(color = Color.Black, fontSize = 24.sp),
                        textAlign = TextAlign.Center
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (page) {
                        0 -> FirstPage(
                            proceedToPageIndex(coroutineScope, page + 1, pagerState),
                            tripState
                        )
                        1 -> SecondPage(
                            proceedToPageIndex(coroutineScope, page + 1, pagerState),
                            tripState
                        )
                        2 -> ThirdPage(tripState.value)
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = pagerState.currentPage == 0 || pagerState.currentPage == 1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(40.dp)
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = DarkGray
            )
        }

        AnimatedVisibility(
            visible = pagerState.currentPage == 2,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .width(175.dp)
        ) {
            Button(
                shape = RoundedCornerShape(30.dp),
                onClick = { onPostNewTrip(tripState.value) },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = LightGray,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(R.string.post_trip),
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.Black, fontSize = 20.sp),
                )
            }
        }
    }

    BackHandler(enabled = pagerState.currentPage == 1 || pagerState.currentPage == 2) {
        when (pagerState.currentPage) {
            1 -> proceedToPageIndex(
                coroutineScope,
                pagerState.currentPage - 1,
                pagerState
            )()
            2 -> proceedToPageIndex(
                coroutineScope,
                pagerState.currentPage - 1,
                pagerState
            )()
        }
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
