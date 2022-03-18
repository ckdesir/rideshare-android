package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.components.RightArrow


@Composable
fun Onboarding1Body(
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = Modifier.weight(1F))
            WelcomeText()
            ProfileImage()
            Box(
                modifier = Modifier
                    .padding(
                        start = 200.dp,
                        top = 50.dp,
                    )
            ){
                RightArrow()
            }
            Spacer(modifier = Modifier.weight(1F))

        }
    }
}

@Composable
fun ProfileImage(){
    Box(
    ){
        Card(
            modifier = Modifier.size(120.dp),
            shape = CircleShape,
            elevation = 2.dp,
            backgroundColor = colorResource(id = R.color.lightgray)
        ) {
//        Image(
//            painterResource(R.drawable.ic_home_icon),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
        }

        Card(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.BottomEnd),
            shape = CircleShape,
            elevation = 2.dp,
            backgroundColor = colorResource(id = R.color.lightgray),
            border = BorderStroke(width = 2.dp, color = Color.White)
        ) {
            Image(
                painterResource(R.drawable.ic_baseline_add_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }


    }
}

@Composable
fun WelcomeText(
) {
    Card(
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.lightgray)),
        shape = RoundedCornerShape(14.dp),
        backgroundColor = colorResource(id = R.color.lightgray),
        modifier = Modifier
            .padding(
                bottom = 20.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .width(150.dp)
        ) {
            Text(
                text = "Hi Lia! Let’s begin by adding a profile picture. "
            )
        }
    }

}



@Preview
@Composable
fun previewText(){
    Onboarding1Body()
}