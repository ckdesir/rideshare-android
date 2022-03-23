package com.cornellappdev.scoop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun RightArrow(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        elevation = 2.dp,
        backgroundColor = colorResource(id = R.color.lightgray),
        border = BorderStroke(width = 2.dp, color =  colorResource(id = R.color.lightgray)),
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    ) {
        Image(
            painterResource(R.drawable.ic_baseline_arrow_forward_24),
            contentDescription = "",

            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
            )
        )
    }
}


//@Preview
//@Composable
//fun PreviewArrow(){
//    RightArrow(
//    )
//}