package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun AboutHeader(
    pagerState: PagerState
){
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier.padding(
            top = 20.dp,
            bottom = 20.dp
        ),
        elevation = 0.dp
    ){
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(width = 0.dp, color = Color.White),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {

                Image(
                    painterResource(R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.padding(
//                        start = 20.dp,
//                    ),
                )
            }


            Spacer(modifier = Modifier.weight(0.5F))
            Text(
                text = "About You",
                fontSize = 25.sp,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.weight(1F))

        }
    }

    
}

//
//@Preview
//@Composable
//fun AboutHeaderPreview(){
//    AboutHeader()
//}