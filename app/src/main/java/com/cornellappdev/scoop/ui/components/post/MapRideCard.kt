package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.User
import com.cornellappdev.scoop.ui.theme.Green

@Composable
fun MapRideCard(
    rideState: MutableState<Ride>
) {
    Spacer(modifier = Modifier.height(40.dp))
    Column() {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(top = 50.dp)
                .padding(start = 40.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.weight(1f))
            Text(
                String.format("%s's drive", rideState.value.creator?.firstName),
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Bottom)
            )
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .align(Bottom)
                    .padding(
                        start = 5.dp,
                        bottom = 5.dp
                    )
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))

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

            Spacer(modifier = Modifier.height(30.dp))

            rideState.value.creator?.let { ProfileMiniCard(it) }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "TRANSPORTATION METHOD",
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row {
                Icon(
                    Icons.Outlined.DriveEta,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(24.dp)
                        .align(CenterVertically)
                )
                Text(
                    text = rideState.value.type ?: "Driving",
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                "LOCATIONS",
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.subtitle2
            )

            Row {
                Icon(
                    Icons.Outlined.NearMe,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(24.dp)
                        .align(CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                rideState.value.departureLocation?.let { departureLocation ->
                    Text(
                        departureLocation,
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.body1,
                    )
                }
            }

            Canvas(
                Modifier
                    .height(10.dp)
                    .wrapContentWidth()
                    .padding(start = 12.dp)
            ) {
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f),
                    strokeWidth = 3f
                )
            }

            Row {
                Icon(
                    Icons.Outlined.Place,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(24.dp)
                        .align(CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                rideState.value.arrivalLocation?.let { arrivalLocation ->
                    Text(
                        arrivalLocation,
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "DEPARTURE DATE",
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.wrapContentWidth()
            ) {
                Icon(
                    Icons.Outlined.CalendarToday,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(29.dp)
                        .align(CenterVertically),
                    contentDescription = stringResource(R.string.calendar_icon_description)
                )

                rideState.value.dateOfTrip?.let { date ->
                    Text(
                        text = date,
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.body1
                    )
                }

                rideState.value.timeOfTrip?.let { time ->
                    Text(
                        text = "@$time",
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "NUMBER OF TRAVELERS",
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                String.format(
                    "%d to %d people",
                    rideState.value.minTravelers, rideState.value.maxTravelers
                ),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "DETAILS",
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = rideState.value.description ?: "None",
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(50.dp))

            /*
        Button(
            onClick = { TODO },
            modifier = Modifier
                .height(50.dp)
                .clip(RoundedCornerShape(100.dp))
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkGreen,
                contentColor = Color.White
            )
        ) {
            Text(
                stringResource(R.string.post_trip),
                style = MaterialTheme.typography.body1.copy(Color.White)
            )
        }
        */
        }
    }
}

@Composable
fun ProfileMiniCard(
    user: User
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            // painter = rememberAsyncImagePainter(user.profilePicUrl),
            painter = painterResource(R.drawable.ic_profile_placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(41.dp)
                .align(CenterVertically)
                .clip(CircleShape)
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = user.firstName + " " + user.lastName,
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.body1
            )
            Row(
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "email icon",
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(
                    text = user.email ?: "No email available",
                    modifier = Modifier.wrapContentWidth(),
                    style = MaterialTheme.typography.body1,
                    fontSize = 14.sp
                )
            }
        }
    }
}