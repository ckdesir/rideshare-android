package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R

@Composable
fun footerImage(
    currIndex: Int,
    carIndex: Int,
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        if(currIndex == carIndex){

            Image(
                painterResource(R.drawable.ic_footer_car),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
        else{

            Image(
                painterResource(R.drawable.footer_green_dot),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
        Image(
            painterResource(R.drawable.loading_black_dot),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Image(
            painterResource(R.drawable.loading_black_dot),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        
    }
}

@OptIn(ExperimentalPagerApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun OnboardingFooter(
    carIndex: Int
) {
    Scaffold(){
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ){
            items(6){
                index ->
                if(index < 5){
                    footerImage(currIndex = index, carIndex = carIndex)
                }
                else{
                    Image(
                        painterResource(R.drawable.green_footer_location),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}