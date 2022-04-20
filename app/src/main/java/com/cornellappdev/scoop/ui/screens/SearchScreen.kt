package com.cornellappdev.scoop.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cornellappdev.scoop.models.Search
import com.cornellappdev.scoop.ui.components.search.FirstPage
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
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
                        0 -> FirstPage(
                            proceedToPageIndex(coroutineScope, page + 1, pagerState),
                            searchState
                        )
                        1 -> null
                    }
                }
            }
        }
    }

    BackHandler(enabled = pagerState.currentPage == 1) {
        when (pagerState.currentPage) {
            1 -> proceedToPageIndex(
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
fun proceedToSearchPageIndex(
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