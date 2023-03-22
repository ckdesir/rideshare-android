package com.cornellappdev.scoop.ui.screens

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideRequestBody
import com.cornellappdev.scoop.ui.components.post.FirstPage
import com.cornellappdev.scoop.ui.components.post.SecondPage
import com.cornellappdev.scoop.ui.components.post.ThirdPage
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.LightGray
import com.cornellappdev.scoop.ui.viewmodel.PostScreenViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
PostScreen holds the FirstPage, SecondPage, and ThirdPage during the
posting flow and passes the rideState back and forth between the other pages
so that they can update the ride's data (We need to change this into a
MVVM (Model View ViewModel) design pattern with a ViewModel for the
PostScreen to network and pass data back and forth.
 */

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun PostScreen(
    onPostNewTrip: (RideRequestBody) -> Unit,
    postScreenViewModel: PostScreenViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
    val rideState = remember {
        mutableStateOf(
            Ride()
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
                            postScreenViewModel
                        )
                        1 -> SecondPage(
                            proceedToPageIndex(coroutineScope, page + 1, pagerState),
                            postScreenViewModel
                        )
                        2 -> ThirdPage(rideState.value)
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
            visible = pagerState.currentPage == 3,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .width(175.dp)
        ) {
            Button(
                shape = RoundedCornerShape(30.dp),
                onClick = { onPostNewTrip(postScreenViewModel.ride) },
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
                0,
                pagerState
            ).invoke()
            2 -> proceedToPageIndex(
                coroutineScope,
                pagerState.currentPage - 1,
                pagerState
            ).invoke()
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
        Log.d("pagerTest", pageIndex.toString())
        coroutineScope.launch {
            pagerState.scrollToPage(pageIndex)
        }
    }
}
