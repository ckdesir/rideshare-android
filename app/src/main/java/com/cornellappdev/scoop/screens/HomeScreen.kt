package com.cornellappdev.scoop.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.ui.theme.DarkGray
import com.cornellappdev.scoop.ui.theme.Gray
import com.cornellappdev.scoop.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun HomeBody(
    onTripClick: (String) -> Unit,
    onPostNewRide: () -> Unit,
    showTripPosted: Boolean = false
) {
    var tripPostedMessageShown by rememberSaveable { mutableStateOf(showTripPosted) }

    // Shows the message about the trip being posted successfully.
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
                    .padding(horizontal = 18.dp)
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
        Column (
            modifier = Modifier
                .background(Color.White)
                .padding(top = 20.dp)
                .padding(horizontal = 10.dp)
        ) {
            Title()
            Spacer(modifier = Modifier.padding(20.dp))
            ActiveTrips()
            Spacer(modifier = Modifier.padding(10.dp))
            PendingTrips()
        }
    }
}

@Composable
fun Title() {
    Row {
        ScoopIcon()
        Text(
            text = stringResource(id = R.string.app_name).lowercase(),
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun BottomBar() {
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(1/3F),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home Icon"
            )
        }
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(1/2F),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        }
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile Icon"
            )
        }
    }
}

@Preview
@Composable
fun MyApp() {
    Scaffold (
        scaffoldState = rememberScaffoldState(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Ride Icon",
                modifier = Modifier.size(20.dp)
            )
        } },
        content = {
            Column (
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 20.dp)
                    .padding(horizontal = 18.dp)
            ) {
                Title()
                Spacer(modifier = Modifier.padding(20.dp))
                ActiveTrips()
                Spacer(modifier = Modifier.padding(10.dp))
                PendingTrips()
            }
        },
        bottomBar = { BottomAppBar(backgroundColor = Color.White)
        { BottomBar() }}
    )
}

@Composable
fun ScoopIcon() {
    Icon(
        imageVector = Icons.Filled.ShoppingCart,
        contentDescription = "Scoop Icon",
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun ActiveTrip() {
    Box (
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(108.dp)
            .background(LightGray, RoundedCornerShape(20.dp))
    )
}

@Composable
fun ActiveTrips() {
    Column () {
        Text(
            text = "Active trips"
        )
        LazyColumn () {
            item {
                ActiveTrip()
            }
            item {
                ActiveTrip()
            }
        }
    }
}

@Composable
fun PendingTrip() {
    Box (
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(108.dp)
            .background(LightGray, RoundedCornerShape(20.dp))
    )
}

@Composable
fun PendingTrips() {
    Column () {
        Text(
            text = "Pending trips"
        )
        LazyColumn () {
            item {
                PendingTrip()
            }
        }
    }
}

/**
 * Builds composable to display the result of if a trip is posted.
 */
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
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = DarkGray,
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 45.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                            painter = painterResource(R.drawable.ic_check_circle),
                            contentDescription = "Trip posted confirmation"
                        )
                        Text(
                            text = stringResource(R.string.trip_posted), // Could maybe alternate to trip hasn't been posted if failure
                            fontSize = 16.sp,
                            modifier = Modifier.padding(16.dp),
                            maxLines = 2,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
