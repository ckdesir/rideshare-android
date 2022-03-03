package com.cornellappdev.scoop.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.Gray
import kotlinx.coroutines.delay

@Composable
fun HomeBody(
    onTripClick: (String) -> Unit,
    onPostNewRide: () -> Unit,
    showTripPosted: Boolean = false
) {
    var tripPostedMessageShown by rememberSaveable { mutableStateOf(showTripPosted) }

    // Shows the message about edit feature.
    suspend fun showTripPosted() {
        if (tripPostedMessageShown) {
            delay(3000L)
            tripPostedMessageShown = false
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(18.dp)
                    .size(70.dp),
                onClick = onPostNewRide,
                backgroundColor = Gray,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_new_trip),
                    contentDescription = "Post a new trip"
                )
            }
        }
    ) {
        TripPostedConfirmation(showTripPosted = tripPostedMessageShown)
        LaunchedEffect(key1 = Unit, block = {
            showTripPosted()
        })
    }
}

@Composable
fun TripPostedConfirmation(showTripPosted: Boolean) {
    AnimatedVisibility(
        visible = showTripPosted,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(120.dp)
                    .height(180.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = DarkGray,
                    elevation = 4.dp
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .width(58.dp)
                                .height(58.dp),
                            painter = painterResource(R.drawable.ic_check_circle),
                            contentDescription = "Trip posted confirmation"
                        )
                        Text(
                            text = stringResource(R.string.trip_posted), // Could maybe alternate to trip hasn't been posted if failure
                            fontSize = 16.sp,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
