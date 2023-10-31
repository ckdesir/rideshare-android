package com.cornellappdev.scoop.ui.components.post

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.RideType

@Composable
fun RideSheet(ride: Ride) {
    Column(modifier = Modifier.padding(top = 28.dp, start = 40.dp, end = 40.dp)) {
        ride.creator?.let { ShortProfileCard(it) }

        Column(modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)){
            Text(
                text= "TRANSPORTATION METHOD",
                style = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            )
            Row(modifier = Modifier.padding(bottom = 15.dp)) {
                Icon(
                    Icons.Filled.DirectionsCar,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .wrapContentSize()
                        .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                val typeText = when (ride.type) {
                    RideType.RIDESHARE -> "Rideshare"
                    RideType.STUDENT -> "Student driver"
                    else -> ""
                }
                ride.type?.let {
                    Text(
                        text = typeText,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontSize = 18.sp),
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(bottom = 15.dp)) {
            Text(
                text = "LOCATIONS",
                style = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            )

            Row {
                Icon(
                    Icons.Outlined.NearMe,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = stringResource(R.string.details_icon_description)
                )
                ride.departureLocationName?.let {
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
                ride.arrivalLocationName?.let {
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
                "${ride.datetime}",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = TextStyle(color = Color.Black, fontSize = 18.sp)
            )
        }

        Column(modifier = Modifier.padding(bottom = 15.dp)) {
            Text(
                text = "NUMBER OF TRAVELLERS",
                style = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            )
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
                    text = "${ride.minTravelers} to ${ride.maxTravelers} people",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = TextStyle(color = Color.Black, fontSize = 18.sp)
                )
            }
        }

        if (ride.description?.isNotBlank() == true) {
            Column {
                Text(
                    text = "DETAILS",
                    style = TextStyle(color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
                )
                Text(
                    ride.description.orEmpty(),
                    style = TextStyle(color = Color.Black, fontSize = 18.sp),
                )
            }

            Spacer(modifier = Modifier.height(150.dp))
        } else {
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}
