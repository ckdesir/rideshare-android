package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.models.Trip

@Composable
fun BottomSheet(trip: Trip) {
    Column(modifier = Modifier.padding(top = 28.dp, start = 40.dp, end = 40.dp)) {
        Text(
            text = "Trip to ${trip.arrivalLocation}",
            style = TextStyle(color = Color.Black, fontSize = 25.sp),
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
            "Organizer: @lia",
            style = TextStyle(color = Color.Black, fontSize = 12.sp),
            modifier = Modifier.padding(bottom = 30.dp)
        )

        Row(modifier = Modifier.padding(bottom = 15.dp)) {
            Icon(
                painterResource(R.drawable.ic_car),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.details_icon_description)
            )
            trip.methodOfTransportation?.let {
                Text(
                    it,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = TextStyle(color = Color.Black, fontSize = 18.sp),
                )
            }
        }

        Column(modifier = Modifier.padding(bottom = 15.dp)) {
            Row {
                Icon(
                    Icons.Outlined.NearMe,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                trip.departureLocation?.let {
                    Text(
                        it,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontSize = 18.sp),
                    )
                }
            }

            Canvas(
                Modifier
                    .height(40.dp)
                    .wrapContentWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
            ) {
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f),
                    strokeWidth = 2f
                )
            }

            Row {
                Icon(
                    Icons.Outlined.Place,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                trip.arrivalLocation?.let {
                    Text(
                        it,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontSize = 18.sp)
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(bottom = 15.dp)) {
            Icon(
                Icons.Outlined.CalendarToday,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.calendar_icon_description)
            )

            Text(
                "${trip.dateOfTrip} @ ${trip.timeOfTrip}",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
        }

        Row(modifier = Modifier.padding(bottom = 15.dp)) {
            Icon(
                Icons.Outlined.Group,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                contentDescription = stringResource(R.string.travelers_icon_description)
            )

            Text(
                "${trip.lowerRangeNumTravelers} to ${trip.higherRangeNumTravelers} other travelers",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
        }

        if (trip.otherDetails?.isNotBlank() == true) {
            Column {
                Text(
                    "Details:\n",
                    style = TextStyle(color = Color.Black, fontSize = 18.sp),
                )
                Text(
                    trip.otherDetails.orEmpty(),
                    style = TextStyle(color = Color.Black, fontSize = 18.sp),
                )
            }

            Spacer(modifier = Modifier.height(150.dp))
        } else {
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}
