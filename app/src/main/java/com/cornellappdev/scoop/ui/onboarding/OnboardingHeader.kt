package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun OnboardingHeader(
    pagerState: PagerState,
    title: String,
){
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .padding(
                bottom = 20.dp
            )
            .fillMaxWidth(),
        elevation = 0.dp
    ) {
        Column(
            Modifier.fillMaxWidth(),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
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
                    )
                }


                Spacer(modifier = Modifier.weight(2F))

                Text(
                    text = title,
                    fontSize = 25.sp,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.weight(0.5F))

                Image(
                    painterResource(R.drawable.header_line_black),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(
                        top = 16.dp,
                    ),
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painterResource(R.drawable.header_line_dotted_green),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }

        }

    }
}

//
//@Preview
//@Composable
//fun AboutHeaderPreview(){
//    AboutHeader()
//}