package com.cornellappdev.scoop.ui.components.general

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.ui.theme.Green

@Composable
fun MapRideCard(
        rideState: MutableState<Ride>
) {
    Column(
            modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp)
    ) {
        Text(
                String.format("%s's ride", rideState.value.creator?.firstName),
                style = TextStyle(color = Color.Black, fontSize = 25.sp)
        )
        Row {
            Icon(imageVector = Icons.Outlined.DriveEta, contentDescription = null)
            Text(
                    text = rideState.value.type ?: "Driving",
                    style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
        }
        Row {
            Icon(
                    Icons.Outlined.NearMe,
                    modifier = Modifier
                            .padding(end = 15.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
            )
            rideState.value.departureLocation?.let { departureLocation ->
                Text(
                        departureLocation,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontSize = 18.sp),
                )
            }
        }

        Canvas(
                Modifier
                        .height(30.dp)
                        .wrapContentWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start = 16.dp)
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
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
            )
            rideState.value.arrivalLocation?.let { arrivalLocation ->
                Text(
                        arrivalLocation,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontSize = 18.sp)
                )
            }
        }

        Row(
                modifier = Modifier.wrapContentWidth()
        ) {
            Icon(
                    Icons.Outlined.CalendarToday,
                    modifier = Modifier
                            .padding(end = 8.dp)
                            .size(29.dp)
                            .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.calendar_icon_description)
            )

            rideState.value.dateOfTrip?.let { date ->
                Text(
                        text = date,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontSize = 18.sp)
                )
            }
        }

        Row(
                modifier = Modifier.wrapContentWidth()
        ) {
            Icon(
                    Icons.Outlined.People,
                    contentDescription = stringResource(R.string.people_icon)
            )

            Text(
                    String.format("%n to %n other travelers",
                            rideState.value.minTravelers, rideState.value.maxTravelers),
                    style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
        }

        Text(
                text = "Details:",
                modifier = Modifier.wrapContentWidth(),
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
        )
        Text(
                text = rideState.value.description ?: "None",
                modifier = Modifier.wrapContentWidth(),
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
        )
        Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Green),
                colors = ButtonDefaults.buttonColors(backgroundColor = Green,
                        contentColor = Color.White)
        ) {
            Text(text = stringResource(R.string.post_trip))
        }
    }
}