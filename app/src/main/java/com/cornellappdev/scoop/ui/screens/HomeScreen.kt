package com.cornellappdev.scoop.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.components.home.TripPostedConfirmation
import com.cornellappdev.scoop.ui.theme.DarkGreen
import com.cornellappdev.scoop.ui.theme.Green
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onTripClick: (String) -> Unit,
    onPostNewRide: () -> Unit,
    showTripPosted: Boolean = false
) {
    var tripPostedMessageShown by rememberSaveable { mutableStateOf(showTripPosted) }

    // Stops displaying the given trip posted message to the user after a delay.
    suspend fun stopDisplayingTripPosted() {
        if (tripPostedMessageShown) {
            delay(3000L)
            tripPostedMessageShown = false
        }
    }

    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Image(
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        painter = painterResource(R.drawable.ic_scoop_logo),
                        contentDescription = stringResource(R.string.scoop_logo)
                    )
                    Row(Modifier.align(Alignment.Bottom)) {
                        Divider(
                            modifier = Modifier
                                .weight(0.8f),
                            color = Color.Black,
                            thickness = 1.dp
                        )
                        Spacer(modifier = Modifier.weight(0.2f))
                    }
                }

                Row(modifier = Modifier.padding(top = 11.dp)) {
                    Spacer(modifier = Modifier.weight(0.2f))
                    Canvas(
                        modifier = Modifier
                            .weight(0.8f)
                    ) {
                        drawLine(
                            color = DarkGreen,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(16f, 8f), 8f),
                            strokeWidth = 2f
                        )
                    }
                }

                Row(modifier = Modifier.padding(top = 11.dp)) {
                    Spacer(modifier = Modifier.weight(0.3f))
                    Divider(
                        modifier = Modifier
                            .weight(0.55f)
                            .padding(bottom = 5.dp),
                        color = Color.Black,
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.weight(0.25f))
                }
            }
        },
        floatingActionButton = {
            Box {
                OutlinedButton(
                    modifier = Modifier
                        .padding(18.dp)
                        .size(80.dp),
                    onClick = onPostNewRide,
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.White,
                        contentColor = Green
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    border = BorderStroke(3.dp, Green),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_new_trip),
                        contentDescription = stringResource(R.string.post_a_new_trip)
                    )
                }
            }
        }
    ) {
        TripPostedConfirmation(showTripPosted = tripPostedMessageShown)
        LaunchedEffect(key1 = Unit, block = {
            stopDisplayingTripPosted()
        })
    }
}
