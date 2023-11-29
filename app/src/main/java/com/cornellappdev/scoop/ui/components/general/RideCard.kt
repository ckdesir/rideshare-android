package com.cornellappdev.scoop.ui.components.general

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.Ride
import com.cornellappdev.scoop.data.models.rideTypeToString
import com.cornellappdev.scoop.ui.theme.BorderGray
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Composable that displays information about the [ride] passed in.
 *
 * @param ride The ride to be turned into a [RideCard]
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RideCard(
    ride: Ride
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = BorderGray),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {

                    ride.creator?.firstName.let { name ->
                        ride.type?.let { transportation ->

                            val rideType = rideTypeToString(transportation)

                            Text(
                                text = "$name's $rideType",
                                style = TextStyle(color = Color.Black, fontSize = 18.sp)
                            )
                        }
                    }
                }

                Row(modifier = Modifier.wrapContentSize()) {

                    Icon(
                        Icons.Filled.CalendarToday,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(29.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.calendar_icon_description)
                    )

                    ride.datetime?.let { time ->

                        val isoDate = LocalDate.parse(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                        val month = isoDate.monthValue
                        val year = isoDate.year

                        Text(
                            text = month.toString() + " " + year.toString(),
                            style = TextStyle(color = Color.Black, fontSize = 18.sp)
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(top = 8.dp)) {
                Row {
                    Icon(
                        Icons.Filled.NearMe,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    ride.path?.departureLocationName?.let { departureLocationName ->
                        Text(
                            departureLocationName,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontSize = 14.sp),
                        )
                    }
                }

                Canvas(
                    Modifier
                        .height(24.dp)
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
                        Icons.Filled.Place,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = stringResource(R.string.details_icon_description)
                    )
                    ride.path?.arrivalLocationName?.let { arrivalLocationName ->
                        Text(
                            arrivalLocationName,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = TextStyle(color = Color.Black, fontSize = 14.sp)
                        )
                    }
                }
            }
        }
    }
}
