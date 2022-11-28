package com.cornellappdev.scoop.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.theme.Green
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun NavHeader(
    pagerState: PagerState?,
    title: String,
    navController: NavController?
) {
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
                            if (pagerState != null) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                            else{
                                navController?.popBackStack()
                            }
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
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
                Canvas(
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                ) {
                    drawLine(
                        color = Green,
                        start = Offset(size.width * 3 / 7, 0f),
                        end = Offset(size.width + 100f, 0f),
                        pathEffect = pathEffect,
                        strokeWidth = 2f
                    )
                }
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