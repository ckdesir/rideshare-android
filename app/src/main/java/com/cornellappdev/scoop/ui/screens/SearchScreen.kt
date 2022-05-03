package com.cornellappdev.scoop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cornellappdev.scoop.models.Search
import com.cornellappdev.scoop.ui.components.search.DisplaySearchesPage
import com.cornellappdev.scoop.ui.components.search.SearchPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen() {
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
    val searchState = remember {
        mutableStateOf(
            Search()
        )
    }

    Box {
        Column {
            HorizontalPager(
                count = 2, state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), userScrollEnabled = false
            ) { page ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (page) {
                        0 -> {
                            SearchPage(
                                {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(page + 1)
                                    }
                                }, searchState
                            )
                        }
                        1 -> DisplaySearchesPage(searchState)
                    }
                }
            }
        }
    }

//    BackHandler(enabled = pagerState.currentPage == 1) {
//        when (pagerState.currentPage) {
//            1 -> proceedToSearchPageIndex(
//                coroutineScope,
//                pagerState.currentPage - 1,
//                pagerState
//            ).invoke()
//        }
//    }
}
