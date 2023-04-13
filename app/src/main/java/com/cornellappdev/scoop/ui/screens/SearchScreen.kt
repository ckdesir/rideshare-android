package com.cornellappdev.scoop.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cornellappdev.scoop.ui.components.search.DisplaySearchesPage
import com.cornellappdev.scoop.ui.components.search.SearchFirstPage
import com.cornellappdev.scoop.ui.viewmodel.SearchScreenViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        count = 2,
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when (page) {
            0 -> SearchFirstPage(
                proceedToPageIndex(coroutineScope, page + 1, pagerState),
                searchScreenViewModel
            )
            1 -> DisplaySearchesPage(
                searchScreenViewModel
            )
        }
    }

    BackHandler(enabled = pagerState.currentPage == 1) {
        proceedToPageIndex(
            coroutineScope,
            0,
            pagerState
        ).invoke()
    }
}

