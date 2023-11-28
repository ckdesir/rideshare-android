package com.cornellappdev.scoop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.ui.theme.*
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun BackArrow(
    pagerState: PagerState,
) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .width(95.dp)
            .height(50.dp),
        shape = RoundedCornerShape(30.dp),
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, color = Color.Black),
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(if (pagerState.currentPage == 0) 0 else pagerState.currentPage - 1)
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Back",
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold,
                color = Green
            )
        }
    }
}
